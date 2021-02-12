package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.Book;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditBookPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String page = ConfigurationManager.getProperty("path.page.login");
        User sessionUser = (User) session.getAttribute(ConfigurationManager.getProperty("user"));

        if (UserRole.ADMIN.equals(sessionUser.getRole())) {
            Book book = null;
            String orderId = request.getParameter("bookId");
            if (orderId != null && !orderId.isEmpty()) {
                book = BookServiceImpl.getInstance().getBook(Integer.valueOf(orderId));
            }
            if (book != null) {
                request.setAttribute("book", book);
            }
            page = ConfigurationManager.getProperty("path.page.admin.edit.book");
        }
        return page;
    }
}
