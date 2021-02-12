package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserListCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            initUserTable(request);
            User user = (User) session.getAttribute(ConfigurationManager.getProperty("user"));
            if (UserRole.ADMIN.equals(user.getRole())) {
                page = ConfigurationManager.getProperty("path.page.admin.users");
            }
        }
        return page;
    }

    private void initUserTable(HttpServletRequest request) {
        UserService userService = UserServiceImpl.getInstance();

        int currentPage = request.getParameter("currentPage") != null ?
                Integer.parseInt(request.getParameter("currentPage")) :
                Integer.parseInt(ConfigurationManager.getProperty("app.currentPage"));
        int recordsPerPage = request.getParameter("recordsPerPage") != null ?
                Integer.parseInt(request.getParameter("recordsPerPage")) :
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage"));

        request.setAttribute("users", userService.getUserList(currentPage));

        int rows = userService.getNumberOfRows();
        int numberOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }
}
