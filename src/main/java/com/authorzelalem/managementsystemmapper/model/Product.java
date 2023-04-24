package com.authorzelalem.managementsystemmapper.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name= "product_Name")
    private String productName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<MaterialItem> materialItem = new HashSet<>();

}
