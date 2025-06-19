package com.example.orders.exepctions;


import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final int code;

    public DomainException(ErrorType errorType , Object... args) {
        super(String.format(errorType.getMessage(), args));
        this.code = errorType.getCode();
    }
}