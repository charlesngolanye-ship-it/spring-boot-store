package com.codewithmosh.store.products;

import lombok.Data;

import java.math.BigDecimal;

@Data // instead of Getter & setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;
}
