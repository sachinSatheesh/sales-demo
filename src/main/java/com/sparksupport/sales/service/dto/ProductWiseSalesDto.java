package com.sparksupport.sales.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductWiseSalesDto {
    private String productId;
    private String productName;
    private float pricePerItem;
    private float totalPrice;
    Set<String> orderIds;
    private float totalQuantity;
}
