
package com.authorzelalem.managementsystemmapper.controller;


import  com.authorzelalem.managementsystemmapper.model.ProductionInventory;
import com.authorzelalem.managementsystemmapper.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/productionInventory")
@Slf4j
@Controller
@AllArgsConstructor
public class ProductionInventoryController {


    private static final String INSERT_OR_UPDATE_FORM = "productionInventory/insertProductionInventoryForm";
    private final MaterialService materialService;
    private final StockInventoryService stockInventoryService;
    private final ProductionInventoryService productionInventoryService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findInventory(Model model){
        model.addAttribute("productsInventory", ProductionInventory.builder().build());
        return "stockInventory/findStockInventory";
    }

    @GetMapping
    public String processFindForm(ProductionInventory productionInventory, BindingResult result, Model model){

        productionInventory = productionInventoryService.findById(productionInventory.getId());
        return "redirect:/customer/" + productionInventory.getId();

    }
    @GetMapping
    @RequestMapping("/{id}")
    public String productionInventory(@PathVariable Long id, Model model) {

        ProductionInventory savedproductionInventory = productionInventoryService.findProductsInventory(id);
        model.addAttribute("stockInventory", savedproductionInventory);
        return "productionInventory/productionInventoryDetails";

    }

    @GetMapping("/{id}/edit")
    public String initUpdateCustomerForm(@PathVariable Long id, Model model ) {
        model.addAttribute("productionInventory", productionInventoryService.findById(id));
        model.addAttribute("material", materialService.findAll());
        return INSERT_OR_UPDATE_FORM;
    }
    @PostMapping("/{id}/edit")
    public String processUpdateCustomerForm(@Valid ProductionInventory productionInventory,
                                            @RequestParam("material.id") Long materialId,
                                            BindingResult result,
                                            @PathVariable Long id) {
        if (result.hasErrors()) {
            return INSERT_OR_UPDATE_FORM;
        } else {
            productionInventory.setId(id);
            ProductionInventory savedproductionInventory= productionInventoryService.save(productionInventory);
            return "redirect:/productionInventory/" + savedproductionInventory.getId();
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomerById(@PathVariable Long id){

        productionInventoryService.deleteById(id);
        return "redirect:/customer/index";
    }

    @GetMapping("/list")
    public String listStockInventory(Model model) {

        Set<ProductionInventory> productionInventoriesList = productionInventoryService.findAll();
        if (productionInventoriesList.isEmpty()) {
            return "customer/notFound";
        } else {
            // List all Customers in database
            model.addAttribute("selections", productionInventoriesList);
            return "productionInventory/productionInventoryList";
        }
    }


//    IN Production Inventory
//    log out a material
//        form
//        deduct the quantity or Size from Production inventory
//         initialQuantity = initialQuantity - selectedProductionInventory.getQuantity();
//         initialSize = initialSize -  selectedProductionInventory.getSize();
//           (material that was in ProductionInventory - material needed to make the product)
//           savedProductionInventoryLength = selectedProductionInventory.getLength - materialNeededForProductLength;
//           savedProductionInventoryWidth = selectedProductionInventory.getWidth - materialNeededForProductWidth;
//           (70*30 roll banner) - (50*10 roll banner used) = (20*20 saved back in Production Inventory)
//                save the leftover materials again in production inventory



// DO LENGTH AND WIDTH FOR SIZE INSTEAD OF STRING
// HOW TO TRACK REMOVED MATERIALS FROM STOCK TP PRODUCTION FOR REPORT


}
