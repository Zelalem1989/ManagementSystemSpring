package com.authorzelalem.managementsystemmapper.repositories;

import com.authorzelalem.managementsystemmapper.model.Customer;
import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepositories extends JpaRepository<OrderItem, Long> {

    @Query("select u from OrderItem u where u.customerId = ?1")
    List<OrderItem> findOrderProducts(Long customerId);

     @Query("select u from OrderItem u where u.customerId = ?1 and u.id = ?2")
     OrderItem findOrderItems(Long customerId, Long orderId );


//    @Query("delete u from OrderItem u where u.customers.id = ?1")
//    void deleteByCustomerId(Long customerId);
}
