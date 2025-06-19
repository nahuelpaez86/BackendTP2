package com.example.practice.execeptions;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String message) { super(message);}
}
