package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.Customer;
import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.Orders;
import com.authorzelalem.managementsystemmapper.model.ProductionInventory;
import jakarta.persistence.Column;

import java.util.List;


public interface OrderService extends CrudService<Orders, Long> {

    //Orders saveOrderDto(Orders orders, OrderItem orderItem, Long product_id, Long quantity, String size, Long Length, Long Width);
    Orders saveOrderDto(Orders orders, OrderItem orderItem, Long product_id);
    void deleteById(Long customerId, Long idToDelete);
    List<Orders> findOrderProducts(Long customerId);

    Orders addOrderMaterial(Customer customer, ProductionInventory productionInventory, Orders orders, OrderItem orderItem, Long product_id);


}
