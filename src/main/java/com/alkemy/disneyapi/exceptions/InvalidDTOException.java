package com.alkemy.disneyapi.exceptions;

public class InvalidDTOException extends RuntimeException{

    /**
     * Exception related to DTOs
     * @param message
     */
    public InvalidDTOException(String message) {
        super(message);
    }
}
