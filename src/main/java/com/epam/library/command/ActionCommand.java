package com.epam.library.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The interface ActionCommand.
 */
public interface ActionCommand {

    /**
     * Execute string.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
