package com.epam.library.model;

import com.epam.library.exception.ApplicationException;
import com.epam.library.resource.ResourceManager;

/**
 * The enum User role.
 */
public enum UserRole {

    /**
     * Admin user role.
     */
    ADMIN,
    /**
     * Librarian user role.
     */
    LIBRARIAN,
    /**
     * User user role.
     */
    USER;

    /**
     * Gets role.
     *
     * @param role the role
     * @return the role
     */
    public static UserRole getRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new ApplicationException(ResourceManager.getInstance().getProperty("message.exception.type"));
        }
        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(ResourceManager.getInstance().getProperty("message.exception.type"), e);
        }
    }
}
