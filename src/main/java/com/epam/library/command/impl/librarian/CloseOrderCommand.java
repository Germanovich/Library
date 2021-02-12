package com.epam.library.command.impl.librarian;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.OrderService;
import com.epam.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CloseOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            OrderService orderService = OrderServiceImpl.getInstance();
            if (request.getParameter("orderId") != null) {
                Integer orderId = Integer.parseInt(request.getParameter("orderId"));
                orderService.closeOrder(orderId);
            }
            RedirectHelper.setRedirectAttribute(request, session);
        }
        return ConfigurationManager.getProperty("path.page.orders");
    }
}
