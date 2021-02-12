package com.epam.library.command.impl.user;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.User;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.resource.ResourceManager;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        UserService userService = UserServiceImpl.getInstance();
        if (Boolean.TRUE.equals(userService.isUserExist(request.getParameter("login"),
                request.getParameter("password")))) {
            User user = userService.getUserByUsernameAndPassword(request.getParameter("login"),
                    request.getParameter("password"));
            HttpSession session = request.getSession(true);
            session.setAttribute(ConfigurationManager.getProperty("user"), user);

            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage",
                    ResourceManager.getInstance().getProperty("message.error.login"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
