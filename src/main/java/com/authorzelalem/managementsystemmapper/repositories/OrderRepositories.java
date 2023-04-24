package com.authorzelalem.managementsystemmapper.repositories;

import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.Orders;
import com.authorzelalem.managementsystemmapper.service.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepositories extends JpaRepository<Orders, Long> {

    @Query("select u from Orders u where u.customers.id = ?1")
    List<Orders> findByCustomerAndOrderId(Long customerId);

    @Query("select u from Orders u where u.customers.id = ?1")
    List<Orders> findOrderProducts(Long customerId);

//    @Query("delete u from Orders u where u.customers.id = ?1")
//    void deleteByCustomerId(Long customerId);

}
