package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.model.*;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.OrderService;
import com.epam.library.service.impl.OrderServiceImpl;
import com.epam.library.util.DataManagerSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditOrderCommand implements ActionCommand {

    private static final String REDIRECT_QUERY = "command=ORDER_LIST";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        OrderService orderService = OrderServiceImpl.getInstance();
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            String id = request.getParameter("id");
            Order order = new OrderBuilder().setBook(new Book(Integer.parseInt(request.getParameter("bookId"))))
                    .setUser(new User(Integer.parseInt(request.getParameter("userId"))))
                    .setStartDate(DataManagerSystem.parseDate(request.getParameter("startDate")))
                    .setEndDate(DataManagerSystem.parseDate(request.getParameter("dateOfCreation")))
                    .setType(OrderType.getType(request.getParameter("orderType")))
                    .setStatus(OrderStatus.getStatus(request.getParameter("orderStatus")))
                    .build();
            if (id == null || id.isEmpty()) {
                orderService.addOrder(order);
            } else {
                order.setId(Integer.parseInt(id));
                orderService.updateOrder(order);
            }
            RedirectHelper.setRedirectAttribute(request, session, REDIRECT_QUERY);
        }
        return page;
    }
}
