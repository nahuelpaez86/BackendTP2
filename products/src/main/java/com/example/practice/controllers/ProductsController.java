package com.example.practice.controllers;


import com.common.dto.ProductDTO;
import com.example.practice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAll() {
        return  productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO editProductByID(@RequestBody ProductDTO product, @PathVariable Long id){
       return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id){
       productService.deleteProduct(id);
    }


    @PutMapping("/{id}/stock/{newStock}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProductStock(@PathVariable Long id,
                                         @PathVariable Integer newStock) {
        return productService.updateProductStock(id, newStock);
    }
}
