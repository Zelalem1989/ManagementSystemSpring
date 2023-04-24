package com.authorzelalem.managementsystemmapper.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;
    private Long customerId;
    @Column(name= "Quantity")
    private Long Quantity;
    @Column(name = "Size")
    private String Size;
    //@Column(name = "Length")
    @Transient
    private Long Length;
    @Transient
    private Long Width;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Orders orders;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "production_inventory_id", referencedColumnName = "production_inventory_id")
    private ProductionInventory productionInventory;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy ="orderItem")
//    private Set<ProductionInventory> productionInventory = new HashSet<>();


//    @OneToMany(cascade = CascadeType.ALL, mappedBy ="orderItem")
//    private Set<ProductionInventory> productionInventory = new HashSet<>();

//    public OrderItem addProductionInventory(ProductionInventory productionInventory){
//        productionInventory.setOrderItem(this);
//        this.productionInventory.add(productionInventory);
//        return this;
//    }

}
