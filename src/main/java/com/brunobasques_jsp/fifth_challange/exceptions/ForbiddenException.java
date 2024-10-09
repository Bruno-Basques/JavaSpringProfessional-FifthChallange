package com.brunobasques_jsp.fifth_challange.exceptions;

@SuppressWarnings("serial")
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String msg) {
        super(msg);
    }
}
