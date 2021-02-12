package com.epam.library.exception;

public class ApplicationException extends RuntimeException {

    /**
     * Instantiates a new ApplicationException.
     *
     * @param message the message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new ApplicationException.
     *
     * @param message the message
     * @param ex      the ex
     */
    public ApplicationException(String message, Exception ex) {
        super(message, ex);
    }
}
