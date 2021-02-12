package com.epam.library.command.factory;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final String NAME_COMMAND = "command";

    private final Logger logger = LogManager.getLogger();

    /**
     * Define command.
     *
     * @param request the request
     * @return the action command
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(NAME_COMMAND);
        logger.debug("Action = " + action);
        if (action == null || action.isEmpty()) {
            return current;
        }
        Command currentEnum = Command.getActionCommand(action);
        current = currentEnum.getCommand();
        return current;
    }
}
