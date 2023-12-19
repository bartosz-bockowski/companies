package com.example.companies.exception;

public class NoEmployeesException extends RuntimeException {
    public NoEmployeesException() {
    }

    public NoEmployeesException(String message) {
        super(message);
    }
}
