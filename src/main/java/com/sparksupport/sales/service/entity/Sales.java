package com.sparksupport.sales.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
public class Sales {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "total_price")
    private float totalPrice;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Sales(String id, Date transactionDate, Product product, float quantity, float totalPrice) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
