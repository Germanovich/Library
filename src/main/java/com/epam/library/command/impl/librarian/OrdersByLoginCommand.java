package com.epam.library.command.impl.librarian;

import com.epam.library.command.ActionCommand;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.OrderService;
import com.epam.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrdersByLoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            initBookTable(request);
            page = ConfigurationManager.getProperty("path.page.orders.by.users.login");
        }
        return page;
    }

    private void initBookTable(HttpServletRequest request) {
        OrderService orderService = OrderServiceImpl.getInstance();

        int currentPage = request.getParameter("currentPage") != null ?
                Integer.parseInt(request.getParameter("currentPage")) :
                Integer.parseInt(ConfigurationManager.getProperty("app.currentPage"));
        int recordsPerPage = request.getParameter("recordsPerPage") != null ?
                Integer.parseInt(request.getParameter("recordsPerPage")) :
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage"));

        String login = request.getParameter("search_text");
        request.setAttribute("orders", orderService.getOrderListByUserLogin(currentPage, login));
        request.setAttribute("search_text", login);

        int rows = orderService.getNumberOfRowsByUserLogin(login);
        int numberOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }
}
