package com.epam.library.dao;

import java.util.List;

/**
 * The interface GenericDao.
 *
 * @param <T> the type parameter
 */
public interface GenericDao<T> {

    /**
     * Add.
     *
     * @param object the object
     */
    void add(T object);

    /**
     * Update boolean.
     *
     * @param object the object
     * @return the boolean
     */
    Boolean update(T object);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Integer id);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    T getById(Integer id);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<T> getAll();
}
