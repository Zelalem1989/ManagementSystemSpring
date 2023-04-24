package com.authorzelalem.managementsystemmapper.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "MaterialItem")
public class MaterialItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_item_id")
    private Long id;
    @Column(name= "product_Name")
    private String productName;
    @Column(name= "Material_Quantity")
    private Long materialQuantity;
    @Column(name = "Material_Size")
    private String materialSize;
    @Column(name = "Material_Length")
    private Long materialLength;
    @Column(name = "Material_Width")
    private Long materialWidth;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
    private Material material;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
    private StockInventory stockInventory;

}
