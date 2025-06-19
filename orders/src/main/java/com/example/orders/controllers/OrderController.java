package com.example.orders.controllers;


import com.example.orders.models.GenericResponse;
import com.example.orders.models.commands.CreateOrderData;
import com.example.orders.models.dto.OrderDTO;
import com.example.orders.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public List<OrderDTO> getMine(@PathVariable Long id) {
        return service.getAllByUserId(id);
    }

    @PostMapping
    public ResponseEntity<GenericResponse> create(@RequestBody  CreateOrderData mt) {
        service.createOrder(mt);
        return ResponseEntity.ok(new GenericResponse(HttpStatus.CREATED.value(), "Orden creada con exito!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse>  update(@PathVariable Long id,
                       @RequestBody OrderDTO newOrder) {
        service.updateOrder(id, newOrder);
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "Orden actualizada con exito!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> delete(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity
                .ok(new GenericResponse(HttpStatus.OK.value(), "Orden eliminado correctamente"));
    }
}