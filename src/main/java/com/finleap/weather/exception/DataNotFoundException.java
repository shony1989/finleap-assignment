package com.finleap.weather.exception;


public class DataNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
