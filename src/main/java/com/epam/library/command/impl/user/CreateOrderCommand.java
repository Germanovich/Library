package com.epam.library.command.impl.user;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.model.Book;
import com.epam.library.model.User;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.OrderService;
import com.epam.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute(ConfigurationManager.getProperty("user"));
            OrderService orderService = OrderServiceImpl.getInstance();
            orderService.addOrder(new OrderBuilder().setBook(new Book(bookId))
                    .setUser(new User(user.getId()))
                    .build());
            request.removeAttribute("bookId");
            request.setAttribute("orders", orderService.getOrderListByUserId(user.getId()));
            RedirectHelper.setRedirectAttribute(request, session);
        }
        return ConfigurationManager.getProperty("path.page.login");
    }
}
