package com.example.orders.controllers;


import com.example.orders.models.GenericResponse;
import com.example.orders.models.commands.CreateOrderData;
import com.example.orders.models.commands.UpdateOrderData;
import com.example.orders.models.dto.OrderDTO;
import com.example.orders.services.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/orders")
@Tag(name = "Orders", description = "Operaciones relacionadas con pedidos")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // ─────────────────────────────────────────────────────────────────────
    @Operation(summary = "Listar todos los pedidos")
    // ─────────────────────────────────────────────────────────────────────


    @GetMapping
    public List<OrderDTO> getAll() {
        return service.getAll();
    }

    // ─────────────────────────────────────────────────────────────────────
    @Operation( summary = "Listar pedidos de un usuario")
    // ─────────────────────────────────────────────────────────────────────


    @GetMapping("/{id}")
    public List<OrderDTO> getMine(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long id) {
        return service.getAllByUserId(id);
    }

    // ─────────────────────────────────────────────────────────────────────
    @Operation(summary = "Crear un nuevo pedido")
    // ─────────────────────────────────────────────────────────────────────

    @PostMapping
    public ResponseEntity<GenericResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para crear el pedido",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CreateOrderData.class)))
            @RequestBody CreateOrderData mt) {

        service.createOrder(mt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new GenericResponse(HttpStatus.CREATED.value(),
                        "¡Orden creada con éxito!"));
    }
    // ─────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Actualizar una orden existente",
            description = "Actualiza los datos de un pedido identificado por su ID , solo actualiza estado y la cantidad del producto pedido. Estados posibles: CREATED y DELIVERED"
    )
    // ─────────────────────────────────────────────────────────────────────


    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> update(
            @Parameter(description = "ID del pedido a actualizar", required = true)
            @PathVariable Long id,
            @RequestBody UpdateOrderData newOrder) {

        service.updateOrder(id, newOrder);
        return ResponseEntity.ok(
                new GenericResponse(HttpStatus.OK.value(),
                        "¡Orden actualizada con éxito!"));
    }

    // ─────────────────────────────────────────────────────────────────────
    @Operation(summary = "Eliminar un pedido")
    // ─────────────────────────────────────────────────────────────────────

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> delete(
            @Parameter(description = "ID del pedido a eliminar", required = true)
            @PathVariable Long id) {

        service.deleteOrder(id);
        return ResponseEntity.ok(
                new GenericResponse(HttpStatus.OK.value(),
                        "¡Orden eliminada correctamente!"));
    }
}
