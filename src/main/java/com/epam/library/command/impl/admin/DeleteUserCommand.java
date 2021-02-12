package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.BookServiceImpl;
import com.epam.library.service.impl.OrderServiceImpl;
import com.epam.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteUserCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.login");
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession(false);

        if (session != null) {
            userService.removeUser(Integer.parseInt(request.getParameter("userId")));

            request.setAttribute("books", BookServiceImpl.getInstance().getBookList());
            request.setAttribute("orders", OrderServiceImpl.getInstance().getOrderList());
            request.setAttribute("users", userService.getUserList());
            RedirectHelper.setRedirectAttribute(request, session);
        }
        return page;
    }
}
