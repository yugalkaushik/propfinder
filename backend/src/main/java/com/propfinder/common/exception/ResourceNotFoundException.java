package com.propfinder.common.exception;

// custom exception which extends Runtime exceptions and do everything what a normal exception can do like carry a message, be thrown, be caught
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}   
