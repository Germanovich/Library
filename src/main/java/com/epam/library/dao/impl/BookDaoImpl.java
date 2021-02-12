package com.epam.library.dao.impl;

import com.epam.library.dao.BookDao;
import com.epam.library.exception.DaoException;
import com.epam.library.model.Book;
import com.epam.library.model.builder.BookBuilder;
import com.epam.library.resource.ResourceManager;
import com.epam.library.resource.SqlManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    private static BookDao instance;

    private BookDaoImpl() {
    }

    public static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDaoImpl();
        }
        return instance;
    }

    @Override
    protected String getSelectQuery() {
        return SqlManager.getProperty("sql.books.find");
    }

    @Override
    protected String getSearchQuery() {
        return SqlManager.getProperty("sql.books.find.by.id");
    }

    @Override
    protected String getCreateQuery() {
        return SqlManager.getProperty("sql.books.add");
    }

    @Override
    protected String getUpdateQuery() {
        return SqlManager.getProperty("sql.books.update");
    }

    @Override
    protected String getDeleteQuery() {
        return SqlManager.getProperty("sql.books.delete");
    }

    @Override
    protected List<Book> getList(ResultSet resultSet) {
        List<Book> bookList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                bookList.add(new BookBuilder().setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("title"))
                        .setAuthorName(resultSet.getString("author_name"))
                        .setDateOfCreation(resultSet.getDate("date_of_creation"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return bookList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Book book) {
        try {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthorName());
            statement.setDate(3, new java.sql.Date(book.getDateOfCreation().getTime()));
            statement.setInt(4, book.getQuantity());
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Book book) {
        try {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthorName());
            statement.setDate(3, new Date(book.getDateOfCreation().getTime()));
            statement.setInt(4, book.getQuantity());
            statement.setInt(5, book.getId());
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
    }

    @Override
    public Integer getNumberOfRows() {
        int number = 0;
        String sql = SqlManager.getProperty("sql.books.count");
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
    public Integer getNumberOfRowsByTitle(String title) {
        int number = 0;
        String sql = SqlManager.getProperty("sql.books.count.by.title");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, title);
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
    public Integer getNumberOfRowsByAuthor(String author) {
        int number = 0;
        String sql = SqlManager.getProperty("sql.books.count.by.author");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, author);
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
    public List<Book> getList(Integer currentPage, Integer recordsPerPage) {
        List<Book> orderList;
        String sql = SqlManager.getProperty("sql.books.find.limit");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, currentPage * recordsPerPage - recordsPerPage);
            statement.setInt(2, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            orderList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return orderList;
    }

    @Override
    public List<Book> getListByName(Integer currentPage, Integer recordsPerPage, String name) {
        List<Book> bookList;
        String sql = SqlManager.getProperty("sql.books.find.by.title.limit");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, currentPage * recordsPerPage - recordsPerPage);
            statement.setInt(3, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            bookList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return bookList;
    }

    @Override
    public List<Book> getListByAuthor(Integer currentPage, Integer recordsPerPage, String author) {
        List<Book> bookList;
        String sql = SqlManager.getProperty("sql.books.find.by.author.limit");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, "%" + author + "%");
            statement.setInt(2, currentPage * recordsPerPage - recordsPerPage);
            statement.setInt(3, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            bookList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return bookList;
    }

    @Override
    public List<Book> getListByDateOfCreation(Integer currentPage, Integer recordsPerPage, java.util.Date date) {
        List<Book> bookList;
        String sql = SqlManager.getProperty("sql.books.find.by.date.limit");
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setDate(1, new Date(date.getTime()));
            ResultSet resultSet = statement.executeQuery();
            bookList = getList(resultSet);
        } catch (SQLException e) {
            logger.error("Error while working with data (database)");
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database.data"));
        }
        return bookList;
    }
}
