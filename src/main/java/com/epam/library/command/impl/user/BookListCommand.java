package com.epam.library.command.impl.user;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.Book;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.BookService;
import com.epam.library.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BookListCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            initBookTable(request);
            User user = (User) session.getAttribute(ConfigurationManager.getProperty("user"));
            if (UserRole.ADMIN.equals(user.getRole())) {
                page = ConfigurationManager.getProperty("path.page.admin.books");
            } else {
                page = ConfigurationManager.getProperty("path.page.books");
            }
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

        List<Book> bookList = bookService.getBookList(currentPage);
        request.setAttribute("books", bookList);

        int rows = bookService.getNumberOfRows();
        int numberOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }
}