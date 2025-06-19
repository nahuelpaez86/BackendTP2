    package com.example.orders.models.mappers;

    import com.common.dto.ProductDTO;
    import com.example.orders.models.dto.OrderDTO;
    import com.example.orders.models.entities.Order;

    public class OrderMapper {
        public static OrderDTO toDTO(Order order, ProductDTO product) {
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setProduct(product);
            dto.setUserId(order.getUserId());
            dto.setQuantity(order.getQuantity());
            dto.setTotal(order.getTotalPrice());
            dto.setStatus(order.getStatus());
            dto.setCreatedAt(order.getCreatedAt());
            return dto;
        }

        public static  Order toEntity(OrderDTO dto) {
            Order order = new Order();
            order.setProductId(dto.getProduct().getId());
            order.setUserId(dto.getUserId());
            order.setQuantity(dto.getQuantity());
            order.setTotalPrice(dto.getTotal());
            order.setStatus(dto.getStatus());
            order.setCreatedAt(dto.getCreatedAt());
            return order;
        }
    }
