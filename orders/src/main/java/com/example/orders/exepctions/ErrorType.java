package com.example.orders.exepctions;

import lombok.Getter;

@Getter
public enum ErrorType {
    // CREATE / UPDATE
    INVALID_QUANTITY                    (1001, "La cantidad debe ser mayor que cero"),
    INSUFFICIENT_STOCK                  (1002, "Stock insuficiente"),

    // READ / UPDATE / DELETE
    ORDER_NOT_FOUND                     (1003, "La orden con ID %d no existe"),
    PRODUCT_NOT_FOUND                   (1004, "El producto con ID %d no existe"),

    // UPDATE
    ORDER_DELIVERED_MODIFICATION_FORBID (1005, "La orden %d ya fue entregada y no se puede modificar"),

    // external services error.
    PRODUCT_SERVICE_UNAVAILABLE         (1500, "Error al comunicarse con el microservicio de productos");

    final int code;
    private final String message;

    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
