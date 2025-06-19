    package com.example.orders.models;


    import lombok.AllArgsConstructor;
    import lombok.Getter;

    @Getter
    @AllArgsConstructor
    public class GenericResponse {
        private int status;
        private String message;
    }