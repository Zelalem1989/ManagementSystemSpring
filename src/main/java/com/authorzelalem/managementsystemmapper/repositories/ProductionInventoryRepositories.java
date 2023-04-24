package com.authorzelalem.managementsystemmapper.repositories;


import com.authorzelalem.managementsystemmapper.model.ProductionInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductionInventoryRepositories extends JpaRepository<ProductionInventory, Long> {

    // this is findById?
    @Query("select u from ProductionInventory u where u.id = ?1")
    ProductionInventory findProductionInventory(Long id);
}
