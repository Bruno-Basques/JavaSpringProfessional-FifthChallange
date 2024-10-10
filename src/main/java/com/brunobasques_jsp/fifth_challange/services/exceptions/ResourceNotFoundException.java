package com.brunobasques_jsp.fifth_challange.services.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
