package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.OrderService;
import com.epam.library.service.impl.BookServiceImpl;
import com.epam.library.service.impl.OrderServiceImpl;
import com.epam.library.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.login");
        OrderService orderService = OrderServiceImpl.getInstance();
        HttpSession session = request.getSession(false);

        if (session != null) {
            orderService.removeOrder(Integer.parseInt(request.getParameter("orderId")));

            request.setAttribute("books", BookServiceImpl.getInstance().getBookList());
            request.setAttribute("orders", orderService.getOrderList());
            request.setAttribute("users", UserServiceImpl.getInstance().getUserList());
            RedirectHelper.setRedirectAttribute(request, session);
        }
        return page;
    }
}
