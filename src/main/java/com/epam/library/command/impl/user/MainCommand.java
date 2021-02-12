package com.epam.library.command.impl.user;

import com.epam.library.command.ActionCommand;
import com.epam.library.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            page = ConfigurationManager.getProperty("path.page.main");
        }
        return page;
    }
}
