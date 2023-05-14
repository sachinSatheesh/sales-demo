package com.sparksupport.sales.service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "order")
@Data
@Transactional
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Date date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderedProducts> orderedProducts;
}
