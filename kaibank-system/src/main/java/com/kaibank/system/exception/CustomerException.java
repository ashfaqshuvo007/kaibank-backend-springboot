package com.kaibank.system.exception;

public class CustomerException extends RuntimeException {
    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
