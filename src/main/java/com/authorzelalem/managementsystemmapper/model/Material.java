package com.authorzelalem.managementsystemmapper.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Long id;
    @Column(name = "Material_Name")
    private String materialName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private Set<MaterialItem> materialItems = new HashSet<>();

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "material")
//    private StockInventory stockInventory;

    @OneToOne(mappedBy = "material" , cascade = CascadeType.ALL, orphanRemoval = true)
    private StockInventory stockInventory;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material", orphanRemoval = true)
//    private ProductionInventory productionInventory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private Set<ProductionInventory> productionInventory  = new HashSet<>();

}
