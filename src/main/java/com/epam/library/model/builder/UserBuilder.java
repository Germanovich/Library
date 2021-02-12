package com.epam.library.model.builder;

import com.epam.library.model.User;
import com.epam.library.model.UserRole;

import java.util.Date;

public class UserBuilder {

    private final User user;

    /**
     * Instantiates a new User builder.
     */
    public UserBuilder() {
        this.user = new User();
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public UserBuilder setId(Integer id) {
        user.setId(id);
        return this;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public UserBuilder setName(String name) {
        user.setName(name);
        return this;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     * @return the surname
     */
    public UserBuilder setSurname(String surname) {
        user.setSurname(surname);
        return this;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     * @return the date of birth
     */
    public UserBuilder setDateOfBirth(Date dateOfBirth) {
        user.setDateOfBirth(dateOfBirth);
        return this;
    }

    /**
     * Sets date of registration.
     *
     * @param dateOfRegistration the date of registration
     * @return the date of registration
     */
    public UserBuilder setDateOfRegistration(Date dateOfRegistration) {
        user.setDateOfRegistration(dateOfRegistration);
        return this;
    }

    /**
     * Sets role.
     *
     * @param role the role
     * @return the role
     */
    public UserBuilder setRole(UserRole role) {
        user.setRole(role);
        return this;
    }

    /**
     * Sets login.
     *
     * @param login the login
     * @return the login
     */
    public UserBuilder setLogin(String login) {
        user.setLogin(login);
        return this;
    }

    /**
     * Sets password.
     *
     * @param password the password
     * @return the password
     */
    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    /**
     * Build user.
     *
     * @return the user
     */
    public User build() {
        return user;
    }
}
