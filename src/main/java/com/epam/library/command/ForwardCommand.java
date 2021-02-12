package com.epam.library.command;

import com.epam.library.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardCommand implements ActionCommand {

    private static final String NAME_PAGE = "page";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String requestPage = request.getParameter(NAME_PAGE);
        if ("login".equals(requestPage)) {
            page = ConfigurationManager.getProperty("path.page.login");
        } else {
            page = ConfigurationManager.getProperty("path.page.registration");
        }
        return page;
    }
}
