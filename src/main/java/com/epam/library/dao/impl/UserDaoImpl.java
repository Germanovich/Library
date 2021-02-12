package com.epam.library.dao.impl;

import com.epam.library.dao.UserDao;
import com.epam.library.exception.DaoException;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.model.builder.UserBuilder;
import com.epam.library.resource.ResourceManager;
import com.epam.library.resource.SqlManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static UserDao instance;

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return SqlManager.getProperty("sql.users.find");
    }

    @Override
    protected String getSearchQuery() {
        return SqlManager.getProperty("sql.users.find.by.id");
    }

    @Override
    protected String getCreateQuery() {
        return SqlManager.getProperty("sql.users.add");
    }

    @Override
    protected String getUpdateQuery() {
        return SqlManager.getProperty("sql.users.update");
    }

    @Override
    protected String getDeleteQuery() {
        return SqlManager.getProperty("sql.users.delete");
    }

    @Override
    protected List<User> getList(ResultSet resultSet) {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                userList.add(new UserBuilder().setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setDateOfBirth(resultSet.getDate("date_of_birth"))
                        .setDateOfRegistration(resultSet.getDate("date_of_registration"))
                        .setRole(UserRole.getRole(resultSet.getString("role")))
                        .setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return userList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User user) {
        try {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
            statement.setTimestamp(4, new Timestamp(user.getDateOfRegistration().getTime()));
            statement.setString(5, user.getRole().name());
            statement.setString(6, user.getLogin());
            statement.setString(7, user.getPassword());
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User user) {
        try {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
            statement.setTimestamp(4, new Timestamp(user.getDateOfRegistration().getTime()));
            statement.setString(5, user.getRole().name());
            statement.setString(6, user.getLogin());
            statement.setString(7, user.getPassword());
            statement.setInt(8, user.getId());
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
    }

    @Override
    public Integer getNumberOfRows() {
        int number = 0;
        String sql = SqlManager.getProperty("sql.users.count");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                number = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return number;
    }

    @Override
    public List<User> getListByRole(UserRole role) {
        List<User> userList;
        String sql = SqlManager.getProperty("sql.users.find.by.role");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, role.name());
            ResultSet resultSet = statement.executeQuery();
            userList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return userList;
    }

    @Override
    public List<User> getAll(Integer currentPage, Integer recordsPerPage) {
        List<User> userList;
        String sql = SqlManager.getProperty("sql.users.find.limit");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, currentPage * recordsPerPage - recordsPerPage);
            statement.setInt(2, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            userList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return userList;
    }

    @Override
    public User getByLogin(String login) {
        List<User> list;
        String sql = SqlManager.getProperty("sql.users.find.by.login");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, login);
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
    public User getByLoginAndPassword(String login, String password) {
        List<User> list;
        String sql = SqlManager.getProperty("sql.users.find.by.login.and.password");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);
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

}
