package com.sparksupport.sales.service.controller;

import com.sparksupport.sales.service.dto.SalesDto;
import com.sparksupport.sales.service.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    SalesService salesService;

    @GetMapping("/get-sales-by-product-id/{productId}")
    public ResponseEntity<Object> addTransaction(@PathVariable("productId") String productId){
        return salesService.getSalesByProductId(productId);
    }

    @GetMapping("/get-total-revenue")
    public ResponseEntity<Object> getRevenue(){
        return salesService.getRevenue();
    }
}
