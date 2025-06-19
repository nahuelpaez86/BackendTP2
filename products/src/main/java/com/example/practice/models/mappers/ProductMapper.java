package com.example.practice.models.mappers;


import com.common.dto.ProductDTO;
import com.example.practice.models.entities.Product;

public class ProductMapper {
    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return product;
    }

    public static ProductDTO toResponseDTO(Product product) {
         ProductDTO dto = new ProductDTO();
         dto.setName(product.getName());
         dto.setDescription(product.getDescription());
         dto.setPrice(product.getPrice());
         dto.setId(product.getId());
         dto.setStock(product.getStock());
         return dto;
    }
}
