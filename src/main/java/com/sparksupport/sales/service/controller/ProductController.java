package com.sparksupport.sales.service.controller;

import com.sparksupport.sales.service.dto.ProductDto;
import com.sparksupport.sales.service.entity.Product;
import com.sparksupport.sales.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAPublicKey;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/insert-product")
    public ResponseEntity<Object> insertProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @GetMapping("/all-products")
    public ResponseEntity<Object> getAllProducts(){
     return  productService.getAllProds();
    }

    @GetMapping("/get-product-by-id")
    public ResponseEntity<Object> getAllProducts(@RequestParam String productId){
        return  productService.getProductById(productId);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<Object> deleteProduct(@RequestParam String productId){
        return productService.deleteProductById(productId);
    }

    @PatchMapping("/update-prpduct")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }
}
