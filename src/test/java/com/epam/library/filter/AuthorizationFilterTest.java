package com.epam.library.filter;

import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.model.builder.UserBuilder;
import com.epam.library.resource.ConfigurationManager;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilterTest {

    @InjectMocks
    private AuthorizationFilter authorizationFilter;

    @Mock
    private HttpSession session;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpServletRequest servletRequest;

    @Mock
    private HttpServletResponse servletResponse;

    @Mock
    private FilterConfig filterConfig;

    private User user;

    private User admin;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new UserBuilder().setRole(UserRole.USER).build();
        admin = new UserBuilder().setRole(UserRole.ADMIN).build();
    }

    @Test
    public void AuthorizationFilter_admin() throws Exception {
        Mockito.when(servletRequest.getSession()).thenReturn(session);
        Mockito.when(servletRequest.getSession(false)).thenReturn(session);
        Mockito.when(servletRequest.getAttribute(ConfigurationManager.getProperty("user"))).thenReturn(new Object());
        Mockito.when(session.getAttribute("sessionUser")).thenReturn(admin);
        Mockito.when(servletRequest.getParameter("command")).thenReturn("USER_LIST");
        authorizationFilter.init(filterConfig);
        authorizationFilter.doFilter(servletRequest, servletResponse, filterChain);
        Mockito.verify(filterChain).doFilter(servletRequest, servletResponse);
    }

    @Test
    public void AuthorizationFilter_user() throws Exception {
        Mockito.when(servletRequest.getSession()).thenReturn(session);
        Mockito.when(servletRequest.getSession(false)).thenReturn(session);
        Mockito.when(servletRequest.getAttribute(ConfigurationManager.getProperty("user"))).thenReturn(new Object());
        Mockito.when(session.getAttribute("sessionUser")).thenReturn(user);
        Mockito.when(servletRequest.getParameter("command")).thenReturn("USER_LIST");
        authorizationFilter.init(filterConfig);
        authorizationFilter.doFilter(servletRequest, servletResponse, filterChain);
        Mockito.verify(servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void AuthorizationFilter_guest() throws Exception {
        Mockito.when(servletRequest.getSession(false)).thenReturn(session);
        Mockito.when(session.getAttribute("sessionUser")).thenReturn(null);
        Mockito.when(servletRequest.getParameter("command")).thenReturn("LOGIN");
        authorizationFilter.doFilter(servletRequest, servletResponse, filterChain);
        Mockito.verify(filterChain).doFilter(servletRequest, servletResponse);
    }
}
