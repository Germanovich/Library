package com.epam.library.controller;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.factory.ActionFactory;
import com.epam.library.exception.ApplicationException;
import com.epam.library.exception.BusinessException;
import com.epam.library.exception.DaoException;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.resource.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Front Controller manages requests and responses. It executes commands which were called,
 * passing them wrapped request data. It then gets results and forwards/redirects to corresponding pages.
 *
 * @see ActionFactory
 * @see ActionCommand
 */
@WebServlet(urlPatterns = "/library")
public class Controller extends HttpServlet {

    private static final String LOCALE_RU = "ru_RU";
    private static final String LOCALE_RU_VALUE = "RU";
    private static final String LOCALE_US_VALUE = "US";

    private final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("locale".equals(cookie.getName())) {
                    request.setAttribute("lang", cookie.getValue());
                    ResourceManager resourceManager = ResourceManager.getInstance();
                    if (LOCALE_RU.equals(cookie.getValue())) {
                        resourceManager.changeResource(new Locale(LOCALE_RU_VALUE, LOCALE_RU_VALUE));
                    } else {
                        resourceManager.changeResource(new Locale(LOCALE_US_VALUE, LOCALE_US_VALUE));
                    }
                    break;
                }
            }
            ActionFactory actionFactory = new ActionFactory();
            ActionCommand actionCommand = actionFactory.defineCommand(request);
            String page = actionCommand.execute(request, response);

            if (page != null) {
                logger.debug("redirect = " + request.getAttribute("redirect"));
                logger.debug("referer = " + request.getHeader("referer"));
                if (request.getAttribute("redirect") != null) {
                    response.sendRedirect(request.getAttribute("redirect").toString());
                } else {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                }
            } else {
                page = ConfigurationManager.getProperty("path.page.index");
                response.sendRedirect(page);
            }
        } catch (ApplicationException | DaoException | BusinessException e) {
            request.getSession().setAttribute("message", e.getMessage());
            response.sendRedirect(ConfigurationManager.getProperty("page.error"));
        }
    }
}
