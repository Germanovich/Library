package com.epam.library.filter;

import com.epam.library.command.factory.Command;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
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
 * Restricts access to some commands.
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"/library"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class AuthorizationFilter implements Filter {

    private final Set<String> adminCommands = new HashSet<>();
    private final Set<String> librarianCommands = new HashSet<>();
    private final Set<String> userCommands = new HashSet<>();

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession currentSession = servletRequest.getSession(false);
        if (currentSession == null || servletRequest.getAttribute(ConfigurationManager.getProperty("user")) == null) {
            chain.doFilter(request, response);
        } else {
            authorize(request, response, chain);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        EnumSet<Command> commands = EnumSet.range(Command.LOGIN, Command.BOOKS_BY_TITLE);
        commands.forEach(command -> userCommands.add(command.name()));

        commands = EnumSet.range(Command.LOGIN, Command.ORDER_LIST);
        commands.forEach(command -> librarianCommands.add(command.name()));

        commands = EnumSet.range(Command.LOGIN, Command.EDIT_BOOK_PAGE);
        commands.forEach(command -> adminCommands.add(command.name()));
    }

    @Override
    public void destroy() {
    }

    private void authorize(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User currentUser = (User) request.getSession().getAttribute(ConfigurationManager.getProperty("user"));

        if (request.getParameter("command") == null) {
            response.sendRedirect(ConfigurationManager.getProperty("page.index"));
            return;
        }
        String command = request.getParameter("command").toUpperCase();
        if (UserRole.ADMIN.equals(currentUser.getRole()) && adminCommands.contains(command)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (UserRole.LIBRARIAN.equals(currentUser.getRole()) && librarianCommands.contains(command)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (UserRole.USER.equals(currentUser.getRole()) && userCommands.contains(command)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}