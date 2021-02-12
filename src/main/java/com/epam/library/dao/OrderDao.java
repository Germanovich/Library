package com.epam.library.dao;

import com.epam.library.model.Order;
import com.epam.library.model.OrderStatus;
import com.epam.library.model.OrderType;

import java.util.List;

/**
 * The interface OrderDao.
 */
public interface OrderDao extends GenericDao<Order> {

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
     * Gets list by user id.
     *
     * @param userId the user id
     * @return the list by user id
     */
    List<Order> getListByUserId(Integer userId);

    /**
     * Gets all.
     *
     * @param currentPage    the current page
     * @param recordsPerPage the records per page
     * @return the all
     */
    List<Order> getAll(Integer currentPage, Integer recordsPerPage);

    /**
     * Gets list by user id.
     *
     * @param currentPage    the current page
     * @param recordsPerPage the records per page
     * @param userId         the user id
     * @return the list by user id
     */
    List<Order> getListByUserId(Integer currentPage, Integer recordsPerPage, Integer userId);

    /**
     * Gets list by user login.
     *
     * @param currentPage    the current page
     * @param recordsPerPage the records per page
     * @param login          the login
     * @return the list by user login
     */
    List<Order> getListByUserLogin(Integer currentPage, Integer recordsPerPage, String login);

    /**
     * Gets list by book id.
     *
     * @param bookId the book id
     * @return the list by book id
     */
    List<Order> getListByBookId(Integer bookId);

    /**
     * Gets list by type.
     *
     * @param type the type
     * @return the list by type
     */
    List<Order> getListByType(OrderType type);

    /**
     * Gets list by status.
     *
     * @param currentPage    the current page
     * @param recordsPerPage the records per page
     * @param status         the status
     * @return the list by status
     */
    List<Order> getListByStatus(Integer currentPage, Integer recordsPerPage, OrderStatus status);
}
