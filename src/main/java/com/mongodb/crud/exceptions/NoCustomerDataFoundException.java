package com.mongodb.crud.exceptions;

public class NoCustomerDataFoundException extends RuntimeException {
    public NoCustomerDataFoundException(String message) {
        super(message);
    }
}
