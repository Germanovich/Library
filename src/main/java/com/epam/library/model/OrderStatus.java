package com.epam.library.model;

import com.epam.library.exception.ApplicationException;
import com.epam.library.resource.ResourceManager;

/**
 * The enum Order status.
 */
public enum OrderStatus {

    /**
     * New order status.
     */
    NEW,
    /**
     * In progress order status.
     */
    IN_PROGRESS,
    /**
     * Closed order status.
     */
    CLOSED;

    /**
     * Gets status.
     *
     * @param status the status
     * @return the status
     */
    public static OrderStatus getStatus(String status) {
        if (status == null || status.isEmpty()) {
            return null;
        }
        try {
            return OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(ResourceManager.getInstance().getProperty("message.exception.status"), e);
        }
    }
}
