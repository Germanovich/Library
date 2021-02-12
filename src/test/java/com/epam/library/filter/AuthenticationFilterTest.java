package com.epam.library.filter;

import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.model.builder.UserBuilder;
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

public class AuthenticationFilterTest {

    @InjectMocks
    private AuthenticationFilter authenticationFilter;

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

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AuthenticationFilter_loggedIn() throws Exception {
        User user = new UserBuilder().setRole(UserRole.USER).build();
        Mockito.when(servletRequest.getSession(false)).thenReturn(session);
        Mockito.when(session.getAttribute("sessionUser")).thenReturn(user);
        Mockito.when(servletRequest.getParameter("command")).thenReturn("main");
        authenticationFilter.doFilter(servletRequest, servletResponse, filterChain);
        Mockito.verify(filterChain).doFilter(servletRequest, servletResponse);
    }

    @Test
    public void AuthenticationFilter_nonLoggedIn() throws Exception {
        Mockito.when(servletRequest.getSession(false)).thenReturn(null);
        Mockito.when(servletRequest.getParameter("command")).thenReturn("login");
        authenticationFilter.init(filterConfig);
        authenticationFilter.doFilter(servletRequest, servletResponse, filterChain);
        Mockito.verify(filterChain).doFilter(servletRequest, servletResponse);
    }
}
