package com.sparksupport.sales.service.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;

@Entity(name = "ordered_products")
@Data
@Transactional
public class OrderedProducts {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column
    private float quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "total_price")
    private float totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}
