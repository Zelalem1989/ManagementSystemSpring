package com.authorzelalem.managementsystemmapper.dto;

import com.authorzelalem.managementsystemmapper.model.Customer;
import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.Orders;
import com.authorzelalem.managementsystemmapper.model.Product;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    @Autowired
    @NonNull
    private Long id;
    @Autowired
    @NonNull
    private Long CustomerId;
    @Autowired
    private Customer customers;
//    @Autowired
//    private Set <OrderItemDto> orderItems = new HashSet<>();

    private OrderItemDto orderItems = new OrderItemDto();


    public OrderDto addOrderItems(OrderItemDto orderItemDto){
        orderItemDto.setSize(orderItemDto.getSize());
        orderItemDto.setQuantity(orderItemDto.getQuantity());
        //this.orderItems.add(orderItemDto);
        System.out.println("this is orderItems set to string " + orderItemDto.toString());
        return this;
    }


}
