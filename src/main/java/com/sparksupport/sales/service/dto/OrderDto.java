package com.sparksupport.sales.service.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private String id;
    private Date date;
    private String userId;
    List<OrderedProductsDto> products;
}
