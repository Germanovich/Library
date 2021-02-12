package com.epam.library.command.impl.user;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.model.User;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.OrderService;
import com.epam.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));

            OrderService orderService = OrderServiceImpl.getInstance();
            orderService.removeOrder(orderId);
            request.removeAttribute("orderId");
            User user = (User) session.getAttribute(ConfigurationManager.getProperty("user"));
            request.setAttribute("orders", orderService.getOrderListByUserId(user.getId()));
            RedirectHelper.setRedirectAttribute(request, session);
        }
        return ConfigurationManager.getProperty("path.page.login");
    }
}
