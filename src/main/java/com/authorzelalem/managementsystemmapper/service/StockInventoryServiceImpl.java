package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.ProductionInventory;
import com.authorzelalem.managementsystemmapper.model.StockInventory;
import com.authorzelalem.managementsystemmapper.repositories.MaterialsRepositories;
import com.authorzelalem.managementsystemmapper.repositories.StockInventoryRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class StockInventoryServiceImpl implements StockInventoryService {

    private final StockInventoryRepositories stockInventoryRepositories;
    private final ProductionInventoryService productionInventoryService;
    private final MaterialsRepositories materialsRepositories;

    @Override
    public StockInventory findStockInventory(Long id) {
        return stockInventoryRepositories.findStockInventory(id);
    }

    //how to logout material. edit?
    @Override
    public Long findByStockId(Long id) {
        return stockInventoryRepositories.findByStockId(id);
    }

    @Override
    public StockInventory saveStockInventory(StockInventory stockInventory) {

        //                Calendar cal = Calendar.getInstance();
//                Date date = cal.getTime();
        System.out.println("stockInventory InitialQuantity  " + stockInventory.getInitialQuantity());
        Long updatedQuantity = (stockInventory.getInitialQuantity() - stockInventory.getQuantity());

        ProductionInventory productionInventory = new ProductionInventory();
        productionInventory.setMaterial(stockInventory.getMaterial());
        productionInventory.setLength(stockInventory.getLength());
        productionInventory.setWidth(stockInventory.getWidth());
        productionInventory.setQuantity(stockInventory.getQuantity());
        productionInventory.setSize(stockInventory.getSize());
        productionInventory.setLoggedInDate(stockInventory.getLoggedOutDate());
        productionInventoryService.save(productionInventory);
        //productionInventory.setIntitalQuantity(stockInventory.getQuantity());

        //Stock logout history
//        StockInventory stockInventoryHistory = new StockInventory();
//        stockInventoryHistory.setMaterial(stockInventory.getMaterial());
//        stockInventoryHistory.setLength(stockInventory.getLength());
//        stockInventoryHistory.setWidth(stockInventory.getWidth());
//        stockInventoryHistory.setSize(stockInventory.getSize());
//        stockInventoryHistory.setQuantity(stockInventory.getQuantity());
//        stockInventoryHistory.setLoggedInDate(stockInventory.getLoggedInDate());
//        stockInventoryHistory.setLoggedOutDate(stockInventory.getLoggedOutDate());
//        stockInventoryHistoryService,save(stockInventoryHistory);


        System.out.println("material Id in stockInventory " + stockInventory.getMaterial().getId());

        stockInventory.setQuantity(updatedQuantity);
        stockInventory.setLoggedOutDate(null);
        // what is left should be saved in saved stocked inventory
        StockInventory savedStockInventory = stockInventoryRepositories.save(stockInventory);

        return savedStockInventory;
    }

    @Override
    public Set<StockInventory> findAll() {
        Set<StockInventory> stockInventory = new HashSet<>();
        stockInventoryRepositories.findAll().forEach(stockInventory::add);
        return stockInventory;
    }

    @Override
    public StockInventory findById(Long aLong) {
        return stockInventoryRepositories.findById(aLong).orElse(null);
    }

    @Override
    public StockInventory save(StockInventory object) {

        return stockInventoryRepositories.save(object);
    }

    @Override
    public void delete(StockInventory object) {
        //object.setMaterial(null);
        stockInventoryRepositories.delete(object);
    }
    @Override
    public void deleteById(Long aLong) {
        stockInventoryRepositories.deleteById(aLong);
    }
}
