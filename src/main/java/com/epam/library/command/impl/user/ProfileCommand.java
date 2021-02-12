package com.epam.library.command.impl.user;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.Order;
import com.epam.library.model.User;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.OrderService;
import com.epam.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            initOrderTable(request, session);
            page = ConfigurationManager.getProperty("path.page.user.profile");
        }
        return page;
    }

    private void initOrderTable(HttpServletRequest request, HttpSession session) {
        OrderService orderService = OrderServiceImpl.getInstance();
        User user = (User) session.getAttribute(ConfigurationManager.getProperty("user"));

        int currentPage = request.getParameter("currentPage") != null ?
                Integer.parseInt(request.getParameter("currentPage")) :
                Integer.parseInt(ConfigurationManager.getProperty("app.currentPage"));
        int recordsPerPage = request.getParameter("recordsPerPage") != null ?
                Integer.parseInt(request.getParameter("recordsPerPage")) :
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage"));

        List<Order> orderList = orderService.getOrderListByUserId(currentPage, user.getId());
        request.setAttribute("orders", orderList);

        int rows = orderService.getNumberOfRowsByUserId(user.getId());
        int numberOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }
}
