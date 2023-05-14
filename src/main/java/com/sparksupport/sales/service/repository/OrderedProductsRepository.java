package com.sparksupport.sales.service.repository;

import com.sparksupport.sales.service.entity.OrderedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts, String> {


    @Query(value = "SELECT * FROM ordered_products where product_id = ?1", nativeQuery = true)
    List<OrderedProducts> findByProductID(String pid);
}
