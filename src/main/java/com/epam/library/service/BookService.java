package com.epam.library.service;

import com.epam.library.model.Book;

import java.util.Date;
import java.util.List;

/**
 * The interface BookService.
 */
public interface BookService {

    /**
     * Add book.
     *
     * @param book the book
     */
    void addBook(Book book);

    /**
     * Remove book.
     *
     * @param id the id
     */
    void removeBook(Integer id);

    /**
     * Update book.
     *
     * @param book the book
     */
    void updateBook(Book book);

    /**
     * Gets number of rows.
     *
     * @return the number of rows
     */
    Integer getNumberOfRows();

    /**
     * Gets number of rows by author.
     *
     * @param author the author
     * @return the number of rows by author
     */
    Integer getNumberOfRowsByAuthor(String author);

    /**
     * Gets number of rows by title.
     *
     * @param author the author
     * @return the number of rows by title
     */
    Integer getNumberOfRowsByTitle(String author);

    /**
     * Gets book.
     *
     * @param id the id
     * @return the book
     */
    Book getBook(Integer id);

    /**
     * Gets book list.
     *
     * @return the book list
     */
    List<Book> getBookList();

    /**
     * Gets book list.
     *
     * @param currentPage the current page
     * @return the book list
     */
    List<Book> getBookList(Integer currentPage);

    /**
     * Gets book list by title.
     *
     * @param currentPage the current page
     * @param title       the title
     * @return the book list by title
     */
    List<Book> getBookListByTitle(Integer currentPage, String title);

    /**
     * Gets book list by author.
     *
     * @param currentPage the current page
     * @param author      the author
     * @return the book list by author
     */
    List<Book> getBookListByAuthor(Integer currentPage, String author);

    /**
     * Gets book list by date of creation.
     *
     * @param currentPage the current page
     * @param date        the date
     * @return the book list by date of creation
     */
    List<Book> getBookListByDateOfCreation(Integer currentPage, Date date);
}
