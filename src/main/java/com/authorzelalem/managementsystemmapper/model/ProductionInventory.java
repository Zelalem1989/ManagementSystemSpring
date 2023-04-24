package com.authorzelalem.managementsystemmapper.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "production_inventory")
public class ProductionInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "production_inventory_id")
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loggedInDate;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loggedOutDate;
    private String Size;
    @Column(name = "Length")
    private Long Length;
    @Column(name = "Width")
    private Long Width;
    @Column(name = "Stock_balance")
    private Long Quantity;


    // should be ManyToMany ???
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
    private Material material;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "order_item_id", referencedColumnName = "order_item_id")
//    private OrderItem orderItem;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockInventory")
//    private Set<Material> materials = new HashSet<>();
}


//FC face Change
//Print
// New