package com.epam.library.command.util;

import com.epam.library.exception.ApplicationException;
import com.epam.library.model.User;
import com.epam.library.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;

public class RedirectHelper {

    private static final String HEADER_REFERER = "referer";

    private static final String ATTRIBUTE_NAME = "redirect";

    private static final String SERVLET_NAME = "library?";

    private RedirectHelper() {
    }

    /**
     * Sets redirect attribute.
     *
     * @param request the request
     * @param session the session
     */
    public static void setRedirectAttribute(HttpServletRequest request, HttpSession session) {
        try {
            URL url = new URL(request.getHeader(HEADER_REFERER));
            if (url.getQuery() == null) {
                User user = (User) session.getAttribute(ConfigurationManager.getProperty("user"));
                if (user != null) {
                    request.setAttribute(ATTRIBUTE_NAME, SERVLET_NAME + "command=MAIN");
                    return;
                }
            }
            request.setAttribute(ATTRIBUTE_NAME, SERVLET_NAME + url.getQuery());
        } catch (MalformedURLException e) {
            throw new ApplicationException("URL malformed.");
        }
    }

    /**
     * Sets redirect attribute.
     *
     * @param request the request
     * @param session the session
     * @param query   the query
     */
    public static void setRedirectAttribute(HttpServletRequest request, HttpSession session, String query) {
        User user = (User) session.getAttribute(ConfigurationManager.getProperty("user"));
        if (user != null) {
            request.setAttribute(ATTRIBUTE_NAME, SERVLET_NAME + query);
            return;
        }
        request.setAttribute(ATTRIBUTE_NAME, SERVLET_NAME + "command=LOGOUT");
    }
}
