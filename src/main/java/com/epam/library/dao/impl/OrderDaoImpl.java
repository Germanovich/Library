package com.epam.library.dao.impl;

import com.epam.library.dao.OrderDao;
import com.epam.library.exception.DaoException;
import com.epam.library.model.*;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.resource.ResourceManager;
import com.epam.library.resource.SqlManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    private static OrderDao instance;

    private OrderDaoImpl() {
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return SqlManager.getProperty("sql.orders.find");
    }

    @Override
    protected String getSearchQuery() {
        return SqlManager.getProperty("sql.orders.find.by.id");
    }

    @Override
    protected String getCreateQuery() {
        return SqlManager.getProperty("sql.orders.add");
    }

    @Override
    protected String getUpdateQuery() {
        return SqlManager.getProperty("sql.orders.update");
    }

    @Override
    protected String getDeleteQuery() {
        return SqlManager.getProperty("sql.orders.delete");
    }

    @Override
    protected List<Order> getList(ResultSet resultSet) {
        List<Order> orderList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderList.add(new OrderBuilder().setId(resultSet.getInt("id"))
                        .setBook(new Book(resultSet.getInt("book_id")))
                        .setUser(new User(resultSet.getInt("user_id")))
                        .setStartDate(resultSet.getDate("start_date"))
                        .setEndDate(resultSet.getDate("end_date"))
                        .setType(OrderType.getType(resultSet.getString("type")))
                        .setStatus(OrderStatus.getStatus(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return orderList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order order) {
        try {
            statement.setInt(1, order.getBook().getId());
            statement.setInt(2, order.getUser().getId());
            statement.setTimestamp(3, new Timestamp(order.getStartDate().getTime()));
            statement.setString(6, order.getOrderStatus().name());
            if (order.getEndDate() == null) {
                statement.setTimestamp(4, null);
            } else {
                statement.setTimestamp(4, new Timestamp(order.getEndDate().getTime()));
            }
            if (order.getOrderType() == null) {
                statement.setString(5, null);
            } else {
                statement.setString(5, order.getOrderType().name());
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order order) {
        try {
            statement.setInt(1, order.getBook().getId());
            statement.setInt(2, order.getUser().getId());
            statement.setTimestamp(3, new Timestamp(order.getStartDate().getTime()));
            statement.setString(6, order.getOrderStatus().name());
            statement.setInt(7, order.getId());
            if (order.getEndDate() == null) {
                statement.setTimestamp(4, null);
            } else {
                statement.setTimestamp(4, new Timestamp(order.getEndDate().getTime()));
            }
            if (order.getOrderType() == null) {
                statement.setString(5, null);
            } else {
                statement.setString(5, order.getOrderType().name());
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
    }

    @Override
    public Integer getNumberOfRows() {
        int number = 0;
        String sql = SqlManager.getProperty("sql.orders.count");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                number = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return number;
    }

    @Override
    public Integer getNumberOfRowsByUserId(Integer userId) {
        int number = 0;
        String sql = SqlManager.getProperty("sql.orders.count.by.users.id");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                number = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return number;
    }

    @Override
    public Integer getNumberOfRowsByUserLogin(String login) {
        int number = 0;
        String sql = SqlManager.getProperty("sql.orders.count.by.users.login");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, "%" + login + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                number = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return number;
    }

    @Override
    public Integer getNumberOfRowsByStatus(OrderStatus status) {
        int number = 0;
        String sql = SqlManager.getProperty("sql.orders.count.by.status");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, status.name());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                number = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return number;
    }

    @Override
    public List<Order> getListByUserId(Integer userId) {
        List<Order> orderList;
        String sql = SqlManager.getProperty("sql.orders.find.by.users.id");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            orderList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return orderList;
    }

    @Override
    public List<Order> getAll(Integer currentPage, Integer recordsPerPage) {
        List<Order> orderList;
        String sql = SqlManager.getProperty("sql.orders.find.limit");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, currentPage * recordsPerPage - recordsPerPage);
            statement.setInt(2, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            orderList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return orderList;
    }

    @Override
    public List<Order> getListByUserId(Integer currentPage, Integer recordsPerPage, Integer userId) {
        List<Order> orderList;
        String sql = SqlManager.getProperty("sql.orders.find.by.users.id.limit");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, currentPage * recordsPerPage - recordsPerPage);
            statement.setInt(3, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            orderList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return orderList;
    }

    @Override
    public List<Order> getListByUserLogin(Integer currentPage, Integer recordsPerPage, String login) {
        List<Order> orderList;
        String sql = SqlManager.getProperty("sql.orders.find.by.users.login.limit");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, "%" + login + "%");
            statement.setInt(2, currentPage * recordsPerPage - recordsPerPage);
            statement.setInt(3, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            orderList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return orderList;
    }

    @Override
    public List<Order> getListByBookId(Integer bookId) {
        List<Order> orderList;
        String sql = SqlManager.getProperty("sql.orders.find.by.books.id");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            orderList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return orderList;
    }

    @Override
    public List<Order> getListByType(OrderType type) {
        List<Order> orderList;
        String sql = SqlManager.getProperty("sql.orders.find.by.type");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, type.name());
            ResultSet resultSet = statement.executeQuery();
            orderList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return orderList;
    }

    @Override
    public List<Order> getListByStatus(Integer currentPage, Integer recordsPerPage, OrderStatus status) {
        List<Order> orderList;
        String sql = SqlManager.getProperty("sql.orders.find.by.status.limit");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, status.name());
            statement.setInt(2, currentPage * recordsPerPage - recordsPerPage);
            statement.setInt(3, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            orderList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return orderList;
    }
}
