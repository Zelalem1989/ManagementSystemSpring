package com.authorzelalem.managementsystemmapper.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
public class ProductDto {

    private Long id;

    private String productName;

//  private Order orders;
    private Set<MaterialDto> materialsSet = new HashSet<>();

}
