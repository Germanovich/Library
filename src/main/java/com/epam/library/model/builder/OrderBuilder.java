package com.epam.library.model.builder;

import com.epam.library.model.*;

import java.util.Date;

public class OrderBuilder {

    private final Order order;

    /**
     * Instantiates a new Order builder.
     */
    public OrderBuilder() {
        this.order = new Order();
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public OrderBuilder setId(Integer id) {
        order.setId(id);
        return this;
    }

    /**
     * Sets book.
     *
     * @param book the book
     * @return the book
     */
    public OrderBuilder setBook(Book book) {
        order.setBook(book);
        return this;
    }

    /**
     * Sets user.
     *
     * @param user the user
     * @return the user
     */
    public OrderBuilder setUser(User user) {
        order.setUser(user);
        return this;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     * @return the start date
     */
    public OrderBuilder setStartDate(Date startDate) {
        order.setStartDate(startDate);
        return this;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     * @return the end date
     */
    public OrderBuilder setEndDate(Date endDate) {
        order.setEndDate(endDate);
        return this;
    }

    /**
     * Sets type.
     *
     * @param type the type
     * @return the type
     */
    public OrderBuilder setType(OrderType type) {
        order.setOrderType(type);
        return this;
    }

    /**
     * Sets status.
     *
     * @param status the status
     * @return the status
     */
    public OrderBuilder setStatus(OrderStatus status) {
        order.setOrderStatus(status);
        return this;
    }

    /**
     * Build order.
     *
     * @return the order
     */
    public Order build() {
        return order;
    }
}
