package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.model.Order;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditOrderPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String page = ConfigurationManager.getProperty("path.page.login");
        User sessionUser = (User) session.getAttribute(ConfigurationManager.getProperty("user"));

        if (UserRole.ADMIN.equals(sessionUser.getRole())) {
            Order order = null;
            String orderId = request.getParameter("orderId");
            if (orderId != null && !orderId.isEmpty()) {
                order = OrderServiceImpl.getInstance().getOrder(Integer.valueOf(orderId));
            }
            if (order != null) {
                request.setAttribute("order", order);
            }
            page = ConfigurationManager.getProperty("path.page.admin.edit.order");
        }
        return page;
    }
}
