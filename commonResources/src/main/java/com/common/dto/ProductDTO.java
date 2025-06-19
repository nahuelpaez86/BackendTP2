package com.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer stock;
}
