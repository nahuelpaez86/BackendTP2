    package com.example.orders.models.dto;

    import com.common.dto.ProductDTO;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.io.Serializable;
    import java.time.LocalDateTime;

    @Getter
    @Setter
    @NoArgsConstructor
    public class OrderDTO implements Serializable {
        private Long id;
        private ProductDTO product;
        private Integer userId;
        private int quantity;
        private double total;
        private String status;
        private LocalDateTime createdAt;

    }
