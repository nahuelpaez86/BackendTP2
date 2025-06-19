package com.example.orders.models.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderData {
    private Long productId;
    private int quantity;
    private Integer userId;
}