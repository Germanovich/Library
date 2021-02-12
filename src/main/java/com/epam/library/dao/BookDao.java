package com.epam.library.dao;

import com.epam.library.model.Book;

import java.util.Date;
import java.util.List;

/**
 * The interface BookDao.
 */
public interface BookDao extends GenericDao<Book> {

    /**
     * Gets number of rows.
     *
     * @return the number of rows
     */
    Integer getNumberOfRows();

    /**
     * Gets number of rows by title.
     *
     * @param title the title
     * @return the number of rows by title
     */
    Integer getNumberOfRowsByTitle(String title);

    /**
     * Gets number of rows by author.
     *
     * @param author the author
     * @return the number of rows by author
     */
    Integer getNumberOfRowsByAuthor(String author);

    /**
     * Gets list.
     *
     * @param currentPage    the current page
     * @param recordsPerPage the records per page
     * @return the list
     */
    List<Book> getList(Integer currentPage, Integer recordsPerPage);

    /**
     * Gets list by name.
     *
     * @param currentPage    the current page
     * @param recordsPerPage the records per page
     * @param name           the name
     * @return the list by name
     */
    List<Book> getListByName(Integer currentPage, Integer recordsPerPage, String name);

    /**
     * Gets list by author.
     *
     * @param currentPage    the current page
     * @param recordsPerPage the records per page
     * @param author         the author
     * @return the list by author
     */
    List<Book> getListByAuthor(Integer currentPage, Integer recordsPerPage, String author);

    /**
     * Gets list by date of creation.
     *
     * @param currentPage    the current page
     * @param recordsPerPage the records per page
     * @param date           the date
     * @return the list by date of creation
     */
    List<Book> getListByDateOfCreation(Integer currentPage, Integer recordsPerPage, Date date);
}
