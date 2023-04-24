package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.ProductionInventory;

public interface ProductionInventoryService extends  CrudService<ProductionInventory, Long>{
    ProductionInventory saveProductsInventory(ProductionInventory productionInventory, Long materialId);
    ProductionInventory findProductsInventory(Long id);
}
