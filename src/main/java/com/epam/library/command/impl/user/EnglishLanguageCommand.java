package com.epam.library.command.impl.user;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.resource.ResourceManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class EnglishLanguageCommand implements ActionCommand {

    private static final String COOKIE_NAME = "locale";
    private static final String COOKIE_VALUE = "en_US";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.login");
        Locale.setDefault(Locale.US);
        ResourceManager.getInstance().changeResource(Locale.US);
        HttpSession session = request.getSession(false);
        Cookie cookie = new Cookie(COOKIE_NAME, COOKIE_VALUE);
        response.addCookie(cookie);

        request.setAttribute("lang", COOKIE_VALUE);
        session.setAttribute("lang", COOKIE_VALUE);

        RedirectHelper.setRedirectAttribute(request, session);
        return page;
    }
}
