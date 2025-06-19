package com.example.orders.services;

import com.example.orders.exepctions.DomainException;
import com.example.orders.exepctions.ErrorType;
import com.example.orders.models.commands.CreateOrderData;
import com.example.orders.models.dto.OrderDTO;
import com.example.orders.models.entities.Order;
import com.example.orders.models.mappers.OrderMapper;
import com.example.orders.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import com.common.dto.ProductDTO;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


@Service
    public class OrderService {
        private final ProductClient productClient;
        private final OrderRepository repository;

        public OrderService(ProductClient productClient , OrderRepository repository) {
            this.productClient = productClient;
            this.repository = repository;
        }

        public List<OrderDTO> getAll() {
            return repository.findAll().stream()
                    .map(this::enrichAndMap)
                    .toList();
        }

        public List<OrderDTO> getAllByUserId(Long currentID) {
            return repository.findByUserId(currentID)
                    .stream()
                    .map(this::enrichAndMap)
                    .toList();
        }

        public void createOrder(CreateOrderData mt) {
            ProductDTO product = updateProductStock(mt.getProductId(),-mt.getQuantity());
            double total = product.getPrice() * mt.getQuantity();
            Order order = new Order(
                    null,
                    mt.getProductId(),
                    mt.getUserId(),
                    mt.getQuantity(),
                    total,
                    "CREATED",
                    LocalDateTime.now()
            );
            repository.save(order);
        }

        public void updateOrder(Long id, OrderDTO newOrder) {
            Order order = repository.findById(id)
                    .orElseThrow(() -> new DomainException(ErrorType.ORDER_NOT_FOUND, id));

            if ("DELIVERED".equals(order.getStatus())) {
                throw new DomainException(ErrorType.ORDER_DELIVERED_MODIFICATION_FORBID,id);
            }

            Integer newQty = newOrder.getQuantity();
            String newStatus = newOrder.getStatus();

            if (newQty <= 0) {
                throw new DomainException(ErrorType.INVALID_QUANTITY);
            }

            if (!Objects.equals(newQty, order.getQuantity())) {

                int delta = newQty - order.getQuantity();

                ProductDTO newProduct = this.updateProductStock(order.getProductId(), -delta);

                double newTotal = newProduct.getPrice() * newQty;
                order.setQuantity(newQty);
                order.setTotalPrice(newTotal);
            }

            if (newStatus != null && !newStatus.isBlank()) {
                order.setStatus(newStatus);
            }

            repository.save(order);
        }

        public void deleteOrder(Long id) {
            Order order = repository.findById(id)
                    .orElseThrow(() -> new DomainException(ErrorType.ORDER_NOT_FOUND,id));

            if(!Objects.equals(order.getStatus(), "DELIVERED")) {
                updateProductStock(order.getProductId(), order.getQuantity());
            }
            repository.delete(order);
        }

        private OrderDTO enrichAndMap(Order order) {
            ProductDTO product = getProduct(order.getProductId());
            OrderDTO dto = OrderMapper.toDTO(order,product);
            dto.setProduct(product);
            return dto;
        }

      // connections with Products microservices

        private ProductDTO getProduct(Long productId) {
            try {
                return productClient.fetchProduct(productId);
            } catch (WebClientResponseException ex) {
                int status = ex.getStatusCode().value();
                if (status == 424) {
                    throw new DomainException(ErrorType.PRODUCT_NOT_FOUND, productId);
                }
                throw new DomainException(ErrorType.PRODUCT_SERVICE_UNAVAILABLE, status);
            } catch (Exception ex) {
                throw new DomainException(ErrorType.PRODUCT_SERVICE_UNAVAILABLE);
            }
        }

        private ProductDTO updateProductStock(Long productId, int delta) {
            ProductDTO product = getProduct(productId);
            int newStock = product.getStock() + delta;

            if (newStock < 0) { throw new DomainException(ErrorType.INSUFFICIENT_STOCK);}

            productClient.updateStock(productId, newStock);
            product.setStock(newStock);
            return product;
        }
    }

