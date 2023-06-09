package com.sparksupport.sales.service.repository;

import com.sparksupport.sales.service.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, String> {
}
