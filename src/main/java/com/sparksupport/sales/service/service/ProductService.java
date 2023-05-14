package com.sparksupport.sales.service.service;

import com.sparksupport.sales.service.dto.ProductDto;
import com.sparksupport.sales.service.entity.Product;
import com.sparksupport.sales.service.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<Object> addProduct(ProductDto productDto){
        Product newProduct = new Product();
        try {
            BeanUtils.copyProperties(productDto, newProduct);
            newProduct = productRepository.save(newProduct);
            productDto.setId(newProduct.getId());     }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error while adding product: "+e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return ResponseEntity.ok(productDto);
    }

    public ResponseEntity<Object> getAllProds(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity<Object> getProductById(String productId){
        ResponseEntity<Object> response;
        try {
            Product product = productRepository.findById(productId).orElseThrow();
            return ResponseEntity.accepted().body(product);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<Object> deleteProductById(String productId){
       try {
           productRepository.deleteById(productId);
           return ResponseEntity.accepted().body("Delete Success");
       }
       catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    public ResponseEntity<Object> updateProduct(ProductDto productDto){
        try {
            Product existingProd = productRepository.findById(productDto.getId()).orElseThrow();
            BeanUtils.copyProperties(productDto, existingProd, getNullPropertyNames(productDto));
            existingProd = productRepository.save(existingProd);
            return ResponseEntity.accepted().body(existingProd);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Method which get null fields
     * @param source
     * @return
     */
    public String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
