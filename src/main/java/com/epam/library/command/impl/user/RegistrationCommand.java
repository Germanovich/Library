package com.epam.library.command.impl.user;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.model.builder.UserBuilder;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.resource.ResourceManager;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.UserServiceImpl;
import com.epam.library.util.DataManagerSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        String page = ConfigurationManager.getProperty("path.page.login");
        User user = new UserBuilder().setName(request.getParameter("name").trim())
                .setSurname(request.getParameter("surname").trim())
                .setDateOfBirth(DataManagerSystem.parseDate(request.getParameter("dateOfBirth")))
                .setPassword(request.getParameter("password").trim())
                .setLogin(request.getParameter("login").trim())
                .setRole(UserRole.getRole(request.getParameter("role")))
                .build();

        if (Boolean.TRUE.equals(userService.isUserExist(user.getLogin()))) {
            request.setAttribute("errorRegisterMessage",
                    ResourceManager.getInstance().getProperty("message.error.register"));
            page = ConfigurationManager.getProperty("path.page.registration");
        } else {
            userService.addUser(user);
        }
        return page;
    }
}