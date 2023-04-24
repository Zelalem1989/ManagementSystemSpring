package com.authorzelalem.managementsystemmapper.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Set<OrderItem> orderItems = new HashSet<>();

    public Orders addOrderItems(OrderItem orderItem){

        orderItem.setOrders(this);
        this.orderItems.add(orderItem);
        return this;
    }
}
