package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.BookService;
import com.epam.library.service.impl.BookServiceImpl;
import com.epam.library.service.impl.OrderServiceImpl;
import com.epam.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteBookCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        BookService bookService = BookServiceImpl.getInstance();
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            bookService.removeBook(Integer.parseInt(request.getParameter("bookId")));

            request.setAttribute("books", bookService.getBookList());
            request.setAttribute("orders", OrderServiceImpl.getInstance().getOrderList());
            request.setAttribute("users", UserServiceImpl.getInstance().getUserList());
            RedirectHelper.setRedirectAttribute(request, session);
        }
        return page;
    }
}
