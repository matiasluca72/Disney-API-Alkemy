package com.alkemy.disneyapi.exceptions;

public class NotFoundException extends RuntimeException {

    /**
     * Exception related to searches which returns nothing
     * @param message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
