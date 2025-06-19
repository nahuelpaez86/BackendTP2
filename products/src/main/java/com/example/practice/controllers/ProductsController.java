package com.example.practice.controllers;


import com.common.dto.ProductDTO;
import com.example.practice.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Operaciones relacionadas con productos")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAll() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Obtener producto por ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @Operation(summary = "Actualizar un producto por ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO editProductByID(@RequestBody ProductDTO product, @PathVariable Long id){
        return productService.updateProduct(id, product);
    }

    @Operation(summary = "Eliminar un producto por ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @Operation(summary = "Actualizar el stock de un producto")
    @PutMapping("/{id}/stock/{newStock}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProductStock(@PathVariable Long id,
                                         @PathVariable Integer newStock) {
        return productService.updateProductStock(id, newStock);
    }
}