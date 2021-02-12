package com.epam.library.service;

import com.epam.library.model.User;
import com.epam.library.model.UserRole;

import java.util.List;

/**
 * The interface UserService.
 */
public interface UserService {

    /**
     * Add user.
     *
     * @param user the user
     */
    void addUser(User user);

    /**
     * Remove user.
     *
     * @param id the id
     */
    void removeUser(Integer id);

    /**
     * Update user.
     *
     * @param user the user
     */
    void updateUser(User user);

    /**
     * Gets number of rows.
     *
     * @return the number of rows
     */
    Integer getNumberOfRows();

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    User getUser(Integer id);

    /**
     * Gets user by username and password.
     *
     * @param login    the login
     * @param password the password
     * @return the user by username and password
     */
    User getUserByUsernameAndPassword(String login, String password);

    /**
     * Is user exist boolean.
     *
     * @param login the login
     * @return the boolean
     */
    Boolean isUserExist(String login);

    /**
     * Is user exist boolean.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     */
    Boolean isUserExist(String login, String password);

    /**
     * Gets user list.
     *
     * @return the user list
     */
    List<User> getUserList();

    /**
     * Gets user list.
     *
     * @param currentPage the current page
     * @return the user list
     */
    List<User> getUserList(Integer currentPage);

    /**
     * Gets user list by role.
     *
     * @param role the role
     * @return the user list by role
     */
    List<User> getUserListByRole(UserRole role);
}
