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

public class RussianLanguageCommand implements ActionCommand {

    private static final String COOKIE_NAME = "locale";
    private static final String COOKIE_VALUE = "ru_RU";
    private static final String LOCALE_VALUE = "ru";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.login");
        Locale rus = new Locale(LOCALE_VALUE, LOCALE_VALUE.toUpperCase());
        Locale.setDefault(rus);
        ResourceManager.getInstance().changeResource(rus);
        HttpSession session = request.getSession(false);
        Cookie cookie = new Cookie(COOKIE_NAME, COOKIE_VALUE);
        response.addCookie(cookie);

        request.setAttribute("lang", COOKIE_VALUE);
        session.setAttribute("lang", COOKIE_VALUE);

        RedirectHelper.setRedirectAttribute(request, session);
        return page;
    }
}
