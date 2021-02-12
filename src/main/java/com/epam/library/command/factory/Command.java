package com.epam.library.command.factory;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.ForwardCommand;
import com.epam.library.command.impl.admin.*;
import com.epam.library.command.impl.librarian.*;
import com.epam.library.command.impl.user.*;
import com.epam.library.exception.ApplicationException;
import com.epam.library.resource.ResourceManager;

/**
 * List of enums with initialized commands inside.
 *
 * @see ActionCommand
 */
public enum Command {
    //Guest commands
    LOGIN(new LoginCommand()),
    REGISTRATION(new RegistrationCommand()),
    ENGLISH_LANGUAGE(new EnglishLanguageCommand()),
    RUSSIAN_LANGUAGE(new RussianLanguageCommand()),
    FORWARD(new ForwardCommand()),
    //User commands and previous
    LOGOUT(new LogoutCommand()),
    MAIN(new MainCommand()),
    BOOK_LIST(new BookListCommand()),
    CANCEL_ORDER(new CancelOrderCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    PROFILE(new ProfileCommand()),
    BOOKS_BY_AUTHOR(new BooksByAuthorCommand()),
    BOOKS_BY_TITLE(new BooksByTitleCommand()),
    //Librarian commands and previous
    ACCEPT_ORDER(new AcceptOrderCommand()),
    CLOSE_ORDER(new CloseOrderCommand()),
    NEW_ORDER_LIST(new NewOrderListCommand()),
    ORDERS_BY_USER_LOGIN(new OrdersByLoginCommand()),
    ORDER_LIST(new OrderListCommand()),
    //Admin commands and previous
    DELETE_USER(new DeleteUserCommand()),
    DELETE_ORDER(new DeleteOrderCommand()),
    DELETE_BOOK(new DeleteBookCommand()),
    USER_LIST(new UserListCommand()),
    EDIT_USER(new EditUserCommand()),
    EDIT_USER_PAGE(new EditUserPageCommand()),
    EDIT_ORDER(new EditOrderCommand()),
    EDIT_ORDER_PAGE(new EditOrderPageCommand()),
    EDIT_BOOK(new EditBookCommand()),
    EDIT_BOOK_PAGE(new EditBookPageCommand());

    private final ActionCommand command;

    Command(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }

    public static Command getActionCommand(String action) {
        if (action == null || action.isEmpty()) {
            throw new ApplicationException(ResourceManager.getInstance().getProperty("message.exception.command"));
        }
        try {
            return Command.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(ResourceManager.getInstance().getProperty("message.exception.command"), e);
        }
    }
}
	
