package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.Orders;
import com.authorzelalem.managementsystemmapper.model.StockInventory;

public interface StockInventoryService  extends  CrudService<StockInventory, Long>{

    StockInventory saveStockInventory(StockInventory stockInventory);
    StockInventory findStockInventory(Long id);
    Long findByStockId(Long id);

}
