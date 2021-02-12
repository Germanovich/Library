package com.epam.library.service.impl;

import com.epam.library.dao.BookDao;
import com.epam.library.dao.OrderDao;
import com.epam.library.dao.UserDao;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.dao.impl.OrderDaoImpl;
import com.epam.library.dao.impl.UserDaoImpl;
import com.epam.library.exception.BusinessException;
import com.epam.library.model.*;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.resource.ResourceManager;
import com.epam.library.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static OrderService instance;

    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final BookDao bookDao = BookDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) {
        order.setStartDate(new Date());
        if (order.getOrderStatus() == null) {
            order.setOrderStatus(OrderStatus.NEW);
        }
        verifyOrderData(order);

        if (bookDao.getById(order.getBook().getId()).getQuantity() == 0 &&
                !OrderStatus.CLOSED.equals(order.getOrderStatus())) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.book.unavailable"));
        }
        if (OrderStatus.IN_PROGRESS.equals(order.getOrderStatus()) &&
                bookDao.getById(order.getBook().getId()).getQuantity() > 0) {
            Book book = bookDao.getById(order.getBook().getId());
            book.setQuantity(book.getQuantity() - 1);
            bookDao.update(book);
            LOGGER.info("Number of copies of the book has been updated");
        }
        orderDao.add(order);
        LOGGER.info("Order added");
    }

    @Override
    public void removeOrder(Integer id) {
        Order order = orderDao.getById(id);
        if (order == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.order.exists"));
        }
        if (OrderStatus.IN_PROGRESS.equals(order.getOrderStatus())) {
            Book book = bookDao.getById(order.getBook().getId());
            book.setQuantity(book.getQuantity() + 1);
            bookDao.update(book);
        }
        orderDao.delete(id);
        LOGGER.info("Order deleted");
    }

    @Override
    public void updateOrder(Order order) {
        verifyOrderData(order);
        if (order.getId() == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.not.found.all.order.data"));
        }
        if (orderDao.getById(order.getId()) == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.order.exists"));
        }
        updateBookQuantity(order);
        orderDao.update(order);
        LOGGER.info("Order updated");
    }

    @Override
    public void updateOrder(Integer id, OrderType type, OrderStatus status) {
        if (type == null ||
                status == null ||
                id == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.not.found.all.order.data"));
        }
        Order order = orderDao.getById(id);
        if (orderDao.getById(order.getId()) == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.order.exists"));
        }
        order.setOrderType(type);
        order.setOrderStatus(status);
        updateBookQuantity(order);
        orderDao.update(order);
        LOGGER.info("Jrder updated");
    }

    @Override
    public void closeOrder(Integer id) {
        Order order = orderDao.getById(id);
        if (orderDao.getById(order.getId()) == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.order.exists"));
        }
        order.setOrderStatus(OrderStatus.CLOSED);
        order.setEndDate(new Date());
        updateBookQuantity(order);
        orderDao.update(order);
        LOGGER.info("Order closed");
    }

    @Override
    public Integer getNumberOfRows() {
        return orderDao.getNumberOfRows();
    }

    @Override
    public Integer getNumberOfRowsByUserId(Integer userId) {
        return orderDao.getNumberOfRowsByUserId(userId);
    }

    @Override
    public Integer getNumberOfRowsByUserLogin(String login) {
        return orderDao.getNumberOfRowsByUserLogin(login);
    }

    @Override
    public Integer getNumberOfRowsByStatus(OrderStatus status) {
        return orderDao.getNumberOfRowsByStatus(status);
    }

    @Override
    public Order getOrder(Integer id) {
        Order order = orderDao.getById(id);
        if (order == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.order.exists"));
        }
        initializeOrder(order);
        return order;
    }

    @Override
    public List<Order> getOrderList() {
        return initializeOrderList(orderDao.getAll());
    }

    @Override
    public List<Order> getOrderList(Integer currentPage) {
        return initializeOrderList(orderDao.getAll(currentPage,
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage"))));
    }

    @Override
    public List<Order> getOrderListByBookId(Integer bookId) {
        return initializeOrderList(orderDao.getListByBookId(bookId));
    }

    @Override
    public List<Order> getOrderListByUserId(Integer userId) {
        return initializeOrderList(orderDao.getListByUserId(userId));
    }

    @Override
    public List<Order> getOrderListByUserId(Integer currentPage, Integer userId) {
        return initializeOrderList(orderDao.getListByUserId(currentPage,
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage")),
                userId));
    }

    @Override
    public List<Order> getOrderListByUserLogin(Integer currentPage, String login) {
        return initializeOrderList(orderDao.getListByUserLogin(currentPage,
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage")),
                login));
    }

    @Override
    public List<Order> getOrderListByType(OrderType type) {
        return initializeOrderList(orderDao.getListByType(type));
    }

    @Override
    public List<Order> getOrderListByStatus(Integer currentPage, OrderStatus status) {
        return initializeOrderList(orderDao.getListByStatus(currentPage,
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage")),
                status));
    }

    private void verifyOrderData(Order order) {
        if (order.getBook() == null ||
                order.getUser() == null ||
                order.getBook().getId() == null ||
                order.getUser().getId() == null ||
                order.getOrderStatus() == null ||
                order.getStartDate() == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.not.found.all.order.data"));
        }
        Book book = bookDao.getById(order.getBook().getId());
        if (book == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.book.exists"));
        }
        User user = userDao.getById(order.getUser().getId());
        if (user == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.user.exists"));
        }
        LOGGER.info("Order verified");
    }

    private List<Order> initializeOrderList(List<Order> orderList) {
        for (Order order : orderList) {
            initializeOrder(order);
        }
        return orderList;
    }

    private void initializeOrder(Order order) {
        Book book = bookDao.getById(order.getBook().getId());
        if (book != null) {
            order.setBook(book);
        }
        User user = userDao.getById(order.getUser().getId());
        if (user != null) {
            order.setUser(user);
        }
    }

    private void updateBookQuantity(Order order) {
        Book book = bookDao.getById(order.getBook().getId());
        OrderStatus oldStatus = orderDao.getById(order.getId()).getOrderStatus();
        OrderStatus newStatus = order.getOrderStatus();

        if (!oldStatus.equals(newStatus)) {
            if (!OrderStatus.IN_PROGRESS.equals(newStatus) &&
                    OrderStatus.IN_PROGRESS.equals(oldStatus)) {
                book.setQuantity(book.getQuantity() + 1);
            } else if (OrderStatus.IN_PROGRESS.equals(newStatus)) {
                if (book.getQuantity() == 0) {
                    throw new BusinessException(ResourceManager.getInstance()
                            .getProperty("message.exception.book.unavailable"));
                } else {
                    book.setQuantity(book.getQuantity() - 1);
                }
            }
            bookDao.update(book);
            LOGGER.info("Number of copies of the book has been updated");
        }
    }
}
