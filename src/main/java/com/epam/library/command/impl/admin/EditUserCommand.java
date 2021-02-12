package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.model.builder.UserBuilder;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.UserServiceImpl;
import com.epam.library.util.DataManagerSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserCommand implements ActionCommand {

    private static final String REDIRECT_QUERY = "command=USER_LIST";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        UserService userService = UserServiceImpl.getInstance();
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            String id = request.getParameter("id");
            User user = new UserBuilder().setName(request.getParameter("name").trim())
                    .setSurname(request.getParameter("surname").trim())
                    .setDateOfBirth(DataManagerSystem.parseDate(request.getParameter("dateOfBirth")))
                    .setDateOfRegistration(DataManagerSystem.parseDate(request.getParameter("dateOfRegistration")))
                    .setRole(UserRole.getRole(request.getParameter("role")))
                    .setLogin(request.getParameter("login").trim())
                    .setPassword(request.getParameter("password").trim())
                    .build();
            if (id == null || id.isEmpty()) {
                if (Boolean.TRUE.equals(userService.isUserExist(user.getLogin()))) {
                    return ConfigurationManager.getProperty("page.error");
                }
                userService.addUser(user);
            } else {
                user.setId(Integer.parseInt(id));
                userService.updateUser(user);
            }
            RedirectHelper.setRedirectAttribute(request, session, REDIRECT_QUERY);
        }
        return page;
    }
}
