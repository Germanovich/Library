package com.epam.library.service.impl;

import com.epam.library.dao.BookDao;
import com.epam.library.dao.OrderDao;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.dao.impl.OrderDaoImpl;
import com.epam.library.exception.BusinessException;
import com.epam.library.model.Book;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.resource.ResourceManager;
import com.epam.library.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static BookService instance;

    private final BookDao bookDao = BookDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    private BookServiceImpl() {
    }

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    @Override
    public void addBook(Book book) {
        verifyBookData(book);
        bookDao.add(book);
        LOGGER.info("Book added");
    }

    @Override
    public void removeBook(Integer id) {
        if (bookDao.getById(id) == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.book.exists"));
        }
        if (orderDao.getListByBookId(id).isEmpty()) {
            bookDao.delete(id);
            LOGGER.info("Book deleted");
            return;
        }
        throw new BusinessException(ResourceManager.getInstance().getProperty("message.exception.book.has.orders"));
    }

    @Override
    public void updateBook(Book book) {
        verifyBookData(book);
        if (book.getId() == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.not.found.all.book.data"));
        }
        if (bookDao.getById(book.getId()) == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.book.exists"));
        }
        bookDao.update(book);
        LOGGER.info("Book updated");
    }

    @Override
    public Integer getNumberOfRows() {
        return bookDao.getNumberOfRows();
    }

    @Override
    public Integer getNumberOfRowsByAuthor(String author) {
        return bookDao.getNumberOfRowsByAuthor(author);
    }

    @Override
    public Integer getNumberOfRowsByTitle(String title) {
        return bookDao.getNumberOfRowsByTitle(title);
    }

    @Override
    public Book getBook(Integer id) {
        Book book = bookDao.getById(id);
        if (book == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.book.exists"));
        }
        return book;
    }

    @Override
    public List<Book> getBookList() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> getBookList(Integer currentPage) {
        return bookDao.getList(currentPage, Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage")));
    }

    @Override
    public List<Book> getBookListByTitle(Integer currentPage, String name) {
        return bookDao.getListByName(currentPage,
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage")),
                name);
    }

    @Override
    public List<Book> getBookListByAuthor(Integer currentPage, String author) {
        return bookDao.getListByAuthor(currentPage,
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage")),
                author);
    }

    @Override
    public List<Book> getBookListByDateOfCreation(Integer currentPage, Date date) {
        return bookDao.getListByDateOfCreation(currentPage,
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage")),
                date);
    }

    private void verifyBookData(Book book) {
        if (book.getTitle() == null ||
                book.getAuthorName() == null ||
                book.getDateOfCreation() == null ||
                book.getQuantity() == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.not.found.all.book.data"));
        }
        LOGGER.info("Book verified");
    }
}
