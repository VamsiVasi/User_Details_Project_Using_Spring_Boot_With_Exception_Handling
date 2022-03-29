package com.albanero.UserDetails_With_Error_Handling.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class Resource_Not_Found_Exception extends Exception{

    private static final long serialVersionUID = 1L;

    public Resource_Not_Found_Exception(String message){
        super(message);
    }
}