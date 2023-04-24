package com.authorzelalem.managementsystemmapper.repositories;

import com.authorzelalem.managementsystemmapper.model.Orders;
import com.authorzelalem.managementsystemmapper.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepositories extends JpaRepository<Product, Long> {


    //select product_id from order_item where order_id = 1;
    @Query("select product.id from OrderItem where orders.id = ?1")
    Long findOrderProducts(Long id);

}
