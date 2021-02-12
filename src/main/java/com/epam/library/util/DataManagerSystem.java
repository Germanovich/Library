package com.epam.library.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataManagerSystem {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private DataManagerSystem() {
    }

    /**
     * Parse date.
     *
     * @param date the date
     * @return the date
     */
    public static Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
