package com.example.orders.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.common.dto.ProductDTO;


// Se conecta con el microservicio Products
@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public ProductDTO fetchProduct(Long id) {
        return webClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductDTO.class)
                .block();
    }

    public ProductDTO updateStock(Long id, Integer newStock) {
        return updateStockAsync(id, newStock).block();
    }

    public Mono<ProductDTO> updateStockAsync(Long id, Integer newStock) {
        return webClient.put()
                .uri("/products/{id}/stock/{newStock}", id , newStock)   // endpoint PATCH
                .retrieve()
                .bodyToMono(ProductDTO.class);
    }


}


