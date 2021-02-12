package com.epam.library.model.builder;

import com.epam.library.model.Book;

import java.util.Date;

public class BookBuilder {

    private final Book book;

    /**
     * Instantiates a new Book builder.
     */
    public BookBuilder() {
        this.book = new Book();
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public BookBuilder setId(Integer id) {
        book.setId(id);
        return this;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public BookBuilder setName(String name) {
        book.setTitle(name);
        return this;
    }

    /**
     * Sets author name.
     *
     * @param authorName the author name
     * @return the author name
     */
    public BookBuilder setAuthorName(String authorName) {
        book.setAuthorName(authorName);
        return this;
    }

    /**
     * Sets date of creation.
     *
     * @param date the date
     * @return the date of creation
     */
    public BookBuilder setDateOfCreation(Date date) {
        book.setDateOfCreation(date);
        return this;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     * @return the quantity
     */
    public BookBuilder setQuantity(Integer quantity) {
        book.setQuantity(quantity);
        return this;
    }

    /**
     * Build book.
     *
     * @return the book
     */
    public Book build() {
        return book;
    }
}
