package com.authorzelalem.managementsystemmapper.dto;

import com.authorzelalem.managementsystemmapper.model.Orders;
import com.authorzelalem.managementsystemmapper.model.Product;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    @Autowired
    @NonNull
    private Long id;
    @Autowired
    @NonNull
    private Long productId;
    @Autowired
    @NonNull
    private Long customerId;
    @Autowired
    @NonNull
    private Long quantity;
    @Autowired
    @NonNull
    private String size;
    @Autowired
    Orders orders;
    @Autowired
    Product product;


}
