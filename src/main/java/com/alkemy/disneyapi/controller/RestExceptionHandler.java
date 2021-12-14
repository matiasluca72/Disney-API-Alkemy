package com.alkemy.disneyapi.controller;

import com.alkemy.disneyapi.dto.ApiErrorDTO;
import com.alkemy.disneyapi.exceptions.InvalidDTOException;
import com.alkemy.disneyapi.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for handling  Exceptions during execution
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles NotFoundExceptions
     * @param ex Exception thrown
     * @param request Request received when exception was thrown
     * @return Error message and an Array listing other errors
     */
    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("Object Requested Not Found")
        );
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles InvaildDTOExceptions
     * @param ex Exception thrown
     * @param request Request received when exception was thrown
     * @return Error message and an Array listing other errors
     */
    @ExceptionHandler(value = {InvalidDTOException.class})
    protected ResponseEntity<Object> handleInvalidDTOException(RuntimeException ex, WebRequest request) {
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("Invalid DTO request.")
        );
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles IllegalArgumentExceptions
     * @param ex Exception thrown
     * @param request Request received when exception was thrown
     * @return Error message
     */
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyResponse = "You should be more specific with application requirements";
        return handleExceptionInternal(ex, bodyResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * Handler for @Valid annotation
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> errors = new ArrayList();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request
        );
    }
}
