package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String page = ConfigurationManager.getProperty("path.page.login");
        User sessionUser = (User) session.getAttribute(ConfigurationManager.getProperty("user"));

        if (UserRole.ADMIN.equals(sessionUser.getRole())) {
            User user = null;
            String userId = request.getParameter("userId");
            if (userId != null && !userId.isEmpty()) {
                user = UserServiceImpl.getInstance().getUser(Integer.valueOf(userId));
            }
            if (user != null) {
                request.setAttribute("user", user);
            }
            page = ConfigurationManager.getProperty("path.page.admin.edit.user");
        }
        return page;
    }
}
