package com.epam.library.service;

import com.epam.library.model.Order;
import com.epam.library.model.OrderStatus;
import com.epam.library.model.OrderType;

import java.util.List;

/**
 * The interface OrderService.
 */
public interface OrderService {

    /**
     * Add order.
     *
     * @param order the order
     */
    void addOrder(Order order);

    /**
     * Remove order.
     *
     * @param id the id
     */
    void removeOrder(Integer id);

    /**
     * Update order.
     *
     * @param order the order
     */
    void updateOrder(Order order);

    /**
     * Update order.
     *
     * @param id     the id
     * @param type   the type
     * @param status the status
     */
    void updateOrder(Integer id, OrderType type, OrderStatus status);

    /**
     * Close order.
     *
     * @param id the id
     */
    void closeOrder(Integer id);

    /**
     * Gets number of rows.
     *
     * @return the number of rows
     */
    Integer getNumberOfRows();

    /**
     * Gets number of rows by user id.
     *
     * @param userId the user id
     * @return the number of rows by user id
     */
    Integer getNumberOfRowsByUserId(Integer userId);

    /**
     * Gets number of rows by user login.
     *
     * @param login the login
     * @return the number of rows by user login
     */
    Integer getNumberOfRowsByUserLogin(String login);

    /**
     * Gets number of rows by status.
     *
     * @param status the status
     * @return the number of rows by status
     */
    Integer getNumberOfRowsByStatus(OrderStatus status);

    /**
     * Gets order.
     *
     * @param id the id
     * @return the order
     */
    Order getOrder(Integer id);

    /**
     * Gets order list.
     *
     * @return the order list
     */
    List<Order> getOrderList();

    /**
     * Gets order list.
     *
     * @param currentPage the current page
     * @return the order list
     */
    List<Order> getOrderList(Integer currentPage);

    /**
     * Gets order list by book id.
     *
     * @param bookId the book id
     * @return the order list by book id
     */
    List<Order> getOrderListByBookId(Integer bookId);

    /**
     * Gets order list by user id.
     *
     * @param userId the user id
     * @return the order list by user id
     */
    List<Order> getOrderListByUserId(Integer userId);

    /**
     * Gets order list by user id.
     *
     * @param currentPage the current page
     * @param userId      the user id
     * @return the order list by user id
     */
    List<Order> getOrderListByUserId(Integer currentPage, Integer userId);

    /**
     * Gets order list by user login.
     *
     * @param currentPage the current page
     * @param login       the login
     * @return the order list by user login
     */
    List<Order> getOrderListByUserLogin(Integer currentPage, String login);

    /**
     * Gets order list by type.
     *
     * @param type the type
     * @return the order list by type
     */
    List<Order> getOrderListByType(OrderType type);

    /**
     * Gets order list by status.
     *
     * @param currentPage the current page
     * @param status      the status
     * @return the order list by status
     */
    List<Order> getOrderListByStatus(Integer currentPage, OrderStatus status);

}
