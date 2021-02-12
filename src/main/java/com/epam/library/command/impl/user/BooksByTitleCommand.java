package com.epam.library.command.impl.user;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.Book;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.BookService;
import com.epam.library.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BooksByTitleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.login");

        HttpSession session = request.getSession(false);
        if (session != null) {
            initBookTable(request);
            page = ConfigurationManager.getProperty("path.page.books.by.title");
        }
        return page;
    }

    private void initBookTable(HttpServletRequest request) {
        BookService bookService = BookServiceImpl.getInstance();

        int currentPage = request.getParameter("currentPage") != null ?
                Integer.parseInt(request.getParameter("currentPage")) :
                Integer.parseInt(ConfigurationManager.getProperty("app.currentPage"));
        int recordsPerPage = request.getParameter("recordsPerPage") != null ?
                Integer.parseInt(request.getParameter("recordsPerPage")) :
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage"));
        String title = request.getParameter("search_text");

        List<Book> bookList = bookService.getBookListByTitle(currentPage, title);
        request.setAttribute("books", bookList);
        request.setAttribute("search_text", title);

        int rows = bookService.getNumberOfRowsByTitle(title);
        int numberOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }
}