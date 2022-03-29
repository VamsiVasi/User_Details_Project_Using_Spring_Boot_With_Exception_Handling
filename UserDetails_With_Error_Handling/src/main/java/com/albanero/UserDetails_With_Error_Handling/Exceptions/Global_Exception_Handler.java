package com.albanero.UserDetails_With_Error_Handling.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class Global_Exception_Handler {
    @ExceptionHandler(Resource_Not_Found_Exception.class)
    public ResponseEntity<?> resourceNotFoundException(Resource_Not_Found_Exception ex, WebRequest request) {
    Error_Details errorDetails = new Error_Details(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExcpetionHandler(Exception ex, WebRequest request) {
        Error_Details errorDetails = new Error_Details(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}