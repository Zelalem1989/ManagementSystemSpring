package com.authorzelalem.managementsystemmapper.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(name= "Name")
    private String Name;
    @Column(name= "Email")
    private String email;
    @Column(name= "PhoneNumber")
    private String phoneNumber;
    @Column(name= "TIN_Number")
    private Long tinNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
    private Set<Orders> orders  = new HashSet<>();

    public Customer addOrder(Orders orders){
        orders.setCustomers(this);
        this.orders.add(orders);
        return this;
    }

}
