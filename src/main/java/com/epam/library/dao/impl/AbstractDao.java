package com.epam.library.dao.impl;

import com.epam.library.dao.GenericDao;
import com.epam.library.dao.util.Connector;
import com.epam.library.exception.DaoException;
import com.epam.library.resource.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T> implements GenericDao<T> {

    protected final Logger logger = LogManager.getLogger();
    private final Connector connector = Connector.getInstance();

    protected AbstractDao() {
    }

    protected abstract String getSelectQuery();

    protected abstract String getSearchQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract List<T> getList(ResultSet resultSet);

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object);

    protected Connection getConnection() {
        return connector.getConnection();
    }

    @Override
    public void add(T object) {
        String sql = getCreateQuery();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
    }

    @Override
    public T getById(Integer id) {
        List<T> list;
        String sql = getSearchQuery();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.iterator().next();
    }

    @Override
    public Boolean update(T object) {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            if (statement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return true;
    }

    @Override
    public void delete(Integer id) {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
    }

    @Override
    public List<T> getAll() {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            list = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return list;
    }
}
