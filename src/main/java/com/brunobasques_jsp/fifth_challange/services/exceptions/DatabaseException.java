package com.brunobasques_jsp.fifth_challange.services.exceptions;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }
}
