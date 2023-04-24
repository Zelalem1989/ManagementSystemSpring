package com.authorzelalem.managementsystemmapper.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Stock")
public class StockInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loggedInDate;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loggedOutDate;
    private String Size;
    // not a colomon
    //@Transient
    @Column(name = "Length")
    private Long Length;
    //@Transient
    @Column(name = "Width")
    private Long Width;
    @Column(name = "Stock_balance")
    private Long Quantity;
    @Transient
    private Long initialQuantity = 0L;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
    private Material material;

//    private Long StockBalance;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "Material_Name", referencedColumnName = "Material_Name")
//    private Material material;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockInventory")
//    private Set<Material> materials = new HashSet<>();

}
