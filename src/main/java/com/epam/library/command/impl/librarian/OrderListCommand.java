package com.epam.library.command.impl.librarian;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.OrderService;
import com.epam.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderListCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            initBookTable(request);
            User user = (User) session.getAttribute(ConfigurationManager.getProperty("user"));
            if (UserRole.ADMIN.equals(user.getRole())) {
                page = ConfigurationManager.getProperty("path.page.admin.orders");
            } else {
                page = ConfigurationManager.getProperty("path.page.orders");
            }
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

        request.setAttribute("orders", orderService.getOrderList(currentPage));

        int rows = orderService.getNumberOfRows();
        int numberOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }
}
