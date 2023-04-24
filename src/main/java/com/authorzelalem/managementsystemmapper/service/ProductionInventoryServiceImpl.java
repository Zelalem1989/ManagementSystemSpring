package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.Product;
import com.authorzelalem.managementsystemmapper.model.ProductionInventory;
import com.authorzelalem.managementsystemmapper.repositories.MaterialsRepositories;
import com.authorzelalem.managementsystemmapper.repositories.ProductionInventoryRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class ProductionInventoryServiceImpl implements ProductionInventoryService {

    private final ProductionInventoryRepositories productionInventoryRepositories;
    private final MaterialsRepositories materialsRepositories;


    @Override
    public void delete(ProductionInventory object) {

        productionInventoryRepositories.delete(object);

    }

    @Override
    public ProductionInventory saveProductsInventory(ProductionInventory productionInventory, Long materialId) {
       // productionInventory.setMaterial( materialsRepositories.getById(materialId));
        ProductionInventory savedProductionInventory = productionInventoryRepositories.save(productionInventory);

        return savedProductionInventory;
    }

    @Override
    public ProductionInventory findProductsInventory(Long id) {
        return productionInventoryRepositories.findProductionInventory(id);
    }

    @Override
    public void deleteById(Long aLong) {

        productionInventoryRepositories.deleteById(aLong);
    }

    @Override
    public ProductionInventory save(ProductionInventory object) {
        return productionInventoryRepositories.save(object);
    }

    @Override
    public ProductionInventory findById(Long aLong) {
        return productionInventoryRepositories.findById(aLong).orElse(null);
    }

    @Override
    public Set<ProductionInventory> findAll() {
        Set<ProductionInventory> productionInventories = new HashSet<>();
        productionInventoryRepositories.findAll().forEach(productionInventories::add);
        return productionInventories;
    }
}
