package com.epam.library.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final Logger logger = LogManager.getLogger();

    private static final Pattern PATTERN_TEXT = Pattern.compile("([A-Za-zА-Яа-я0-9]){3,20}");

    private static final Pattern PATTERN_PASSWORD = Pattern.compile("([A-Za-zА-Яа-я0-9]){6,20}");


    /**
     * Validate login, name and surname.
     *
     * @param string the enter login, name and surname.
     * @return true, if successful
     */
    public static boolean validateString(String string) {
        Matcher matcher = PATTERN_TEXT.matcher(string);
        logger.debug("Validate string: " + matcher.matches());
        return matcher.matches();
    }

    /**
     * Validate password.
     *
     * @param password the enter password
     * @return true, if successful
     */
    public static boolean validatePassword(String password) {
        Matcher matcher = PATTERN_PASSWORD.matcher(password);
        logger.debug("Validate password: " + matcher.matches());
        return matcher.matches();
    }
}
