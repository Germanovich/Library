package com.epam.library.dao;

import com.epam.library.model.User;
import com.epam.library.model.UserRole;

import java.util.List;

/**
 * The interface UserDao.
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Gets number of rows.
     *
     * @return the number of rows
     */
    Integer getNumberOfRows();

    /**
     * Gets list by role.
     *
     * @param role the role
     * @return the list by role
     */
    List<User> getListByRole(UserRole role);

    /**
     * Gets all.
     *
     * @param currentPage    the current page
     * @param recordsPerPage the records per page
     * @return the all
     */
    List<User> getAll(Integer currentPage, Integer recordsPerPage);

    /**
     * Gets by login.
     *
     * @param login the login
     * @return the by login
     */
    User getByLogin(String login);

    /**
     * Gets by login and password.
     *
     * @param login    the login
     * @param password the password
     * @return the by login and password
     */
    User getByLoginAndPassword(String login, String password);
}
