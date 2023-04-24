package com.authorzelalem.managementsystemmapper.repositories;

import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.StockInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockInventoryRepositories extends JpaRepository<StockInventory, Long> {

    //findById ?
    @Query("select u from StockInventory u where u.id = ?1")
    StockInventory findStockInventory(Long id);

    @Query("select material.id from StockInventory u where u.id = ?1")
    Long findByStockId(Long id);
}
