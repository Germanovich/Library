package com.epam.library.model;

import com.epam.library.exception.ApplicationException;
import com.epam.library.resource.ResourceManager;

/**
 * The enum Order type.
 */
public enum OrderType {

    /**
     * Subscription order type.
     */
    SUBSCRIPTION,
    /**
     * Reading room order type.
     */
    READING_ROOM;

    /**
     * Gets type.
     *
     * @param type the type
     * @return the type
     */
    public static OrderType getType(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        try {
            return OrderType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(ResourceManager.getInstance().getProperty("message.exception.type"), e);
        }
    }
}
