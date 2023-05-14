package com.sparksupport.sales.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SalesDto {

    private String id;
    private float quantity;
    private String productId;
    private Date transactionDate;
    private float totalPrice;
}
