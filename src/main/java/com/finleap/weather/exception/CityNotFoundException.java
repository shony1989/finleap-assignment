package com.finleap.weather.exception;

public class CityNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public CityNotFoundException(String message) {
        super(message);
    }
}
