package com.sparksupport.sales.service.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@Transactional
public class Product {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "price_per_quantity")
    private String perQuantity;
}
