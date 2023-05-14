package com.sparksupport.sales.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private String id;
    private String name;
    private String type;
    private boolean active;
    private String description;
    private float price;
    private String perQuantity;
}
