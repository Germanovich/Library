package com.epam.library.command.impl.librarian;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.model.OrderStatus;
import com.epam.library.model.OrderType;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.OrderService;
import com.epam.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AcceptOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Integer orderId = Integer.parseInt(request.getParameter("orderId"));
            OrderType type = OrderType.getType(request.getParameter("orderType"));
            request.removeAttribute("orderId");
            request.removeAttribute("orderStatus");

            OrderService orderService = OrderServiceImpl.getInstance();
            orderService.updateOrder(orderId, type, OrderStatus.IN_PROGRESS);
            RedirectHelper.setRedirectAttribute(request, session);
        }
        return ConfigurationManager.getProperty("path.page.login");
    }
}
