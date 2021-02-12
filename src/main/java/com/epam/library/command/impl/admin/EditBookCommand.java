package com.epam.library.command.impl.admin;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.util.RedirectHelper;
import com.epam.library.model.Book;
import com.epam.library.model.builder.BookBuilder;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.service.BookService;
import com.epam.library.service.impl.BookServiceImpl;
import com.epam.library.util.DataManagerSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditBookCommand implements ActionCommand {

    private static final String REDIRECT_QUERY = "command=BOOK_LIST";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        BookService bookService = BookServiceImpl.getInstance();
        String page = ConfigurationManager.getProperty("path.page.login");

        if (session != null) {
            String id = request.getParameter("id");
            Book book = new BookBuilder().setName(request.getParameter("title").trim())
                    .setAuthorName(request.getParameter("authorName").trim())
                    .setDateOfCreation(DataManagerSystem.parseDate(request.getParameter("dateOfCreation")))
                    .setQuantity(Integer.parseInt(request.getParameter("quantity")))
                    .build();
            if (id == null || id.isEmpty()) {
                bookService.addBook(book);
            } else {
                book.setId(Integer.parseInt(id));
                bookService.updateBook(book);
            }
            RedirectHelper.setRedirectAttribute(request, session, REDIRECT_QUERY);
        }
        return page;
    }
}
