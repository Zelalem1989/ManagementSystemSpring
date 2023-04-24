package com.authorzelalem.managementsystemmapper.dto;

import com.authorzelalem.managementsystemmapper.model.Product;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

public class MaterialDto {
    @Autowired
    private Long id;
    @Autowired
    private String materialName;
    @Autowired
    private Product product;
}
