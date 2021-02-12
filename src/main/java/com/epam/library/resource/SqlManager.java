package com.epam.library.resource;

import java.util.ResourceBundle;

public class SqlManager {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }

    private SqlManager() {
    }
}
