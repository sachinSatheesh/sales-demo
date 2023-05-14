package com.sparksupport.sales.service.service;

import com.sparksupport.sales.service.dto.OrderDto;
import com.sparksupport.sales.service.dto.ProductWiseSalesDto;
import com.sparksupport.sales.service.entity.*;
import com.sparksupport.sales.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SalesService {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
            @Lazy
    OrderedProductsRepository orderedProductsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<Object> getSalesByProductId(String productId){
        try{
            List<OrderedProducts> orderedProducts = orderedProductsRepository.findByProductID(productId);
            if(orderedProducts == null || orderedProducts.isEmpty())
                throw  new RuntimeException("No orders found for this product.");
            ProductWiseSalesDto salesDto = new ProductWiseSalesDto();
            salesDto.setProductId(productId);
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException(" invalid product id"));
            salesDto.setProductName(product.getName());
            salesDto.setPricePerItem(product.getPrice());
            Set<String> orderIds = new HashSet<>();
            for(OrderedProducts eachProduct : orderedProducts) {
                if(eachProduct.getProduct().getId().equals(productId)) {
                    salesDto.setTotalQuantity(salesDto.getTotalQuantity() + eachProduct.getQuantity());
                    salesDto.setTotalPrice(salesDto.getTotalPrice() + eachProduct.getTotalPrice());
                    if(!orderIds.contains(eachProduct.getOrder().getId()))
                        orderIds.add(eachProduct.getOrder().getId());
                }
            }
            salesDto.setOrderIds(orderIds);
            return ResponseEntity.accepted().body(salesDto);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @Transactional
    public ResponseEntity<Object> placeOrder(OrderDto orderDto){
        try {
            if(orderDto.getUserId() == null || orderDto.getProducts() == null)
                throw new RuntimeException("Bad request. Please check the request payload.");
            User user = userRepository.findById(orderDto.getUserId()).orElseThrow(() -> new RuntimeException(" user id is invalid."));
            List<OrderedProducts> orderedProductsList = new ArrayList<>();
            Order newOrder = new Order();
            orderDto.getProducts().forEach(item -> {
                Optional<Product> product = productRepository.findById(item.getProductId());
                if(product.isPresent()){
                    OrderedProducts orderedProducts = new OrderedProducts();
                    orderedProducts.setProduct(product.get());
                    orderedProducts.setQuantity(item.getQuantity());
                    orderedProducts.setTotalPrice(item.getQuantity() * product.get().getPrice());
                    orderedProductsList.add(orderedProducts);
                }
            });
            newOrder.setDate(new Date());
            newOrder.setUser(user);
            newOrder.setOrderedProducts(orderedProductsList);
            newOrder = orderRepository.save(newOrder);
            return ResponseEntity.accepted().body(newOrder);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<Object> getRevenue(){
        try{
            List<OrderedProducts> orderedProductsList = orderedProductsRepository.findAll();
            if(orderedProductsList == null || orderedProductsList.isEmpty())
                throw new RuntimeException("No sale happened yet");
            Map<String, ProductWiseSalesDto> salesDtoMap = new HashMap<>();
            orderedProductsList.forEach(item -> {
                if(!salesDtoMap.containsKey(item.getProduct().getId()))
                {
                    Optional<Product> product = productRepository.findById(item.getProduct().getId());
                    ProductWiseSalesDto productWiseSalesDto = new ProductWiseSalesDto();
                    if(product.isPresent()) {
                        productWiseSalesDto.setProductId(product.get().getId());
                        productWiseSalesDto.setProductName(product.get().getName());
                        productWiseSalesDto.setPricePerItem(product.get().getPrice());
                        productWiseSalesDto.setTotalPrice(item.getTotalPrice());
                        productWiseSalesDto.setTotalQuantity(item.getQuantity());
                    }
                    salesDtoMap.put(product.get().getId(), productWiseSalesDto);
                }
                else {
                    ProductWiseSalesDto productWiseSalesDto = salesDtoMap.get(item.getProduct().getId());
                    productWiseSalesDto.setTotalPrice(productWiseSalesDto.getTotalPrice() + item.getTotalPrice());
                    productWiseSalesDto.setTotalQuantity(productWiseSalesDto.getTotalQuantity()+item.getQuantity());
                    salesDtoMap.put(item.getProduct().getId(), productWiseSalesDto);
                }
            });
            return ResponseEntity.accepted().body(salesDtoMap);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
