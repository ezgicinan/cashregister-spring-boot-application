package com.ezgicinan.springbootapplication.exceptions;

public class CustomerNotExistException extends IllegalArgumentException {
    public CustomerNotExistException(String msg) {
        super(msg);
    }
}
