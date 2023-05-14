package com.sparksupport.sales.service.controller;

import com.sparksupport.sales.service.dto.OrderDto;
import com.sparksupport.sales.service.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    SalesService salesService;

    @PostMapping("/place-order")
    public ResponseEntity<Object> orderItem(@RequestBody OrderDto orderDto){
        return salesService.placeOrder(orderDto);
    }
}
