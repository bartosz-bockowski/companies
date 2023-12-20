package com.example.companies.exception;

public class MustBeNullException extends RuntimeException {

    public MustBeNullException(String message) {
        super(message);
    }

}
