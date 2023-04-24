package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemService extends CrudService<OrderItem, Long>{

    List<OrderItem> findOrderProducts(Long customerId);

    OrderItem findOrderItems(Long customerId, Long orderId);



}
