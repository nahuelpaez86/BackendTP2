package com.example.practice.services;


import com.common.dto.ProductDTO;
import com.example.practice.execeptions.ProductNotExistException;
import com.example.practice.models.entities.Product;
import com.example.practice.models.mappers.ProductMapper;
import com.example.practice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class ProductService  {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) { this.repository = repository; }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product newProduct = ProductMapper.toEntity(productDTO);
        repository.save(newProduct);
        return productDTO;
    }

    public ProductDTO getProductById(Long id) {
        return repository.findById(id).map(ProductMapper::toResponseDTO)
                .orElseThrow(()-> new ProductNotExistException("Producto no existe."));
    }

    public List<ProductDTO> getAllProducts() {
        return repository.findAll().stream()
                .map(ProductMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO updateProduct(Long id, ProductDTO updatedProductDTO) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ProductNotExistException("Product not exit"));
        existing.setName(updatedProductDTO.getName());
        existing.setDescription(updatedProductDTO.getDescription());
        existing.setPrice(updatedProductDTO.getPrice());
        repository.save(existing);
        return updatedProductDTO;
    }

    public ProductDTO updateProductStock(Long id, Integer newStock) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ProductNotExistException("Product not exist"));

        existing.setStock(newStock);
        repository.save(existing);

        ProductDTO dto = new ProductDTO();
        dto.setId(existing.getId());
        dto.setName(existing.getName());
        dto.setPrice(existing.getPrice());
        dto.setDescription(existing.getDescription());
        dto.setStock(existing.getStock());

        return dto;
    }

    public void deleteProduct(Long id) {
        Product productById = repository.findById(id)
                        .orElseThrow(() -> new ProductNotExistException("Product not exit"));
        repository.delete(productById);
    }
}
