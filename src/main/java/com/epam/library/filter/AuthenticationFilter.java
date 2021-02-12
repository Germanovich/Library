package com.epam.library.filter;

import com.epam.library.command.factory.Command;
import com.epam.library.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Defines granted commands for guests and restricts access to other commands
 * for unauthorized users.
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/library"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class AuthenticationFilter implements Filter {

    private final Set<String> guestCommands = new HashSet<>();

    /**
     * Adds granted commands for guests from EnumSet.
     *
     * @param filterConfig
     * @see Command
     */
    @Override
    public void init(FilterConfig filterConfig) {
        EnumSet<Command> guestCommands = EnumSet.range(Command.LOGIN, Command.FORWARD);
        guestCommands.forEach(command -> this.guestCommands.add(command.name()));
    }

    /**
     * Filters request and response if user is logged in
     * or if granted commands contain requested command.
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession currentSession = request.getSession(false);

        boolean userLoggedIn = currentSession != null &&
                currentSession.getAttribute(ConfigurationManager.getProperty("user")) != null;

        if (request.getParameter("command") == null) {
            response.sendRedirect(ConfigurationManager.getProperty("page.index"));
            return;
        }
        String command = request.getParameter("command").toUpperCase();

        if (guestCommands.contains(command) || userLoggedIn) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(ConfigurationManager.getProperty("page.index"));
        }
    }

    @Override
    public void destroy() {
    }
}
