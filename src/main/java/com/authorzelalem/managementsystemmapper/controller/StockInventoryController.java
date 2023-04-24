package com.authorzelalem.managementsystemmapper.controller;

import com.authorzelalem.managementsystemmapper.model.Material;
import com.authorzelalem.managementsystemmapper.model.MaterialItem;
import com.authorzelalem.managementsystemmapper.model.Product;
import com.authorzelalem.managementsystemmapper.model.StockInventory;
import com.authorzelalem.managementsystemmapper.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@RequestMapping("/stockInventory")
@Slf4j
@Controller
@AllArgsConstructor
public class StockInventoryController {


        private static final String MATERIAL_LOGOUT_FORM = "stockInventory/materialLogoutForm";
        private static final String INSERT_OR_UPDATE_FORM = "stockInventory/insertStockInventoryForm";
        private final MaterialService materialService;
        private final StockInventoryService stockInventoryService;

        @InitBinder
        public void setAllowedFields(WebDataBinder dataBinder) {
            dataBinder.setDisallowedFields("id");
        }

        @GetMapping
        @RequestMapping("/new")
        public String initCreationForm(Model model) {

            Set<Material> materialList = materialService.findAll();
            model.addAttribute("stockInventory", new StockInventory());
            model.addAttribute("material", materialList);

            return INSERT_OR_UPDATE_FORM;
        }

        @PostMapping("/new")
        public String processCreationForm(@Valid StockInventory stockInventory,
                                          @RequestParam("material.id") Long materialId,
                                          BindingResult result) {

//            this means select a material and enter quantity and size to enter.
//                    not create a new stack in vetory  idea add to anexisting order if it has the same size
            if (result.hasErrors()) {
                return INSERT_OR_UPDATE_FORM;
            } else {

                System.out.println("Material in stock  " + stockInventory.getMaterial().getId());
                stockInventory.setMaterial(materialService.findById(materialId));
                System.out.println("Material in stock  " + stockInventory.getMaterial().getId());
                //StockInventory savedStock = stockInventoryService.saveStockInventory(stockInventory, materialId);
                StockInventory savedStock = stockInventoryService.save(stockInventory);


                return "redirect:/stockInventory/" + savedStock.getId();
            }
        }

        @GetMapping
        @RequestMapping("/{id}")
        public String showStockInventory(@PathVariable Long id, Model model) {

           // StockInventory savedStock = stockInventoryService.findStockInventory(id);
            StockInventory savedStock = stockInventoryService.findById(id);
            model.addAttribute("stockInventory", savedStock);
            return "stockInventory/stockInventoryDetails";

        }

        @RequestMapping("/find")
        public String findInventory(Model model){
            model.addAttribute("stockInventory", StockInventory.builder().build());
            return "stockInventory/findStockInventory";
        }

        @GetMapping
        public String processFindForm(StockInventory stockInventory, BindingResult result, Model model){

            stockInventory = stockInventoryService.findById(stockInventory.getId());
            return "redirect:/stockInventory/" + stockInventory.getId();

        }
        @GetMapping("/{id}/edit")
        public String initUpdateStockInventoryForm(@PathVariable Long id, Model model ) {
            model.addAttribute("stockInventory", stockInventoryService.findById(id));
            model.addAttribute("material", materialService.findAll());
            return INSERT_OR_UPDATE_FORM;
        }
        @PostMapping("/{id}/edit")
        public String processUpdateStockInventoryForm(@Valid StockInventory stockInventory,
                                                      @RequestParam("material.id") Long materialId,
                                                      BindingResult result,
                                                      @PathVariable Long id) {
            if (result.hasErrors()) {
                return INSERT_OR_UPDATE_FORM;
            } else {
                stockInventory.setId(id);
                StockInventory savedStockInventory = stockInventoryService.save(stockInventory);
                return "redirect:/stockInventory/" + savedStockInventory.getId();
            }
        }


        @GetMapping("/{id}/materialLogout")
        public String initLogoutStockInventoryForm(@PathVariable Long id,
                                                   Model model) {
            StockInventory stockInventory = stockInventoryService.findById(id);
            stockInventory.setInitialQuantity(stockInventory.getQuantity());
            Material material = stockInventory.getMaterial();
            model.addAttribute("stockInventory", stockInventory);
            model.addAttribute("material", material);

            return MATERIAL_LOGOUT_FORM;
        }
        @PostMapping("/{id}/materialLogout")
        public String processLogoutStockInventoryForm(@Valid StockInventory stockInventory,
                                                      @RequestParam("material.id") Long materialId,
                                                      BindingResult result,
                                                      @PathVariable Long id) {


            //  see if you can do hidden input for login date size
            if (result.hasErrors()) {
                return MATERIAL_LOGOUT_FORM;
            } else {
// for log out time and log in time automatic ???
//                Calendar cal = Calendar.getInstance();
//                Date date = cal.getTime();
                  //Long finalQuantity = (stockInventory.getInitialQuantity() - stockInventory.getQuantity());
                  stockInventory.setId(id);
//                  Long finalQuantity = (stockInventory.getInitialQuantity() - stockInventory.getQuantity());
//                  stockInventory.setQuantity(finalQuantity);
                  StockInventory savedStockInventory = stockInventoryService.saveStockInventory(stockInventory);

                return "redirect:/stockInventory/" + savedStockInventory.getId();
            }
        }

    /**** Material from Stock is slelected  *****/

//            Logout material from StockInventory
//             selectedStockInventory = new StockInventory();

//            get it from the form
//            put it in productionInventory
//
//            material logged out of Stock to ProductInventory
//            ProductInventory productInventory = selectedStockInventoryService.save(selectedStockInventory);
//            remove it from Stock Inventory  (MAYBE PUT IT IN HISTORY??)
//
//            material needed for the product
//            ProductInventory productionInProgress = new ProductInventory();
//
//            Form > productionInProgress
//
//               deduct the Size of material needed from size of material taken out from StockInventory
//                   initialQuantity = productInventory.getQuantity = productionInProgress.getQuantity;
//                   initialLength(LeftOver)  = productInventory.getLength - productionInProgress.Length;
//                   initialWidth(LeftOver)   = productInventory.getWidth - productionInProgress.Width;
//                   (0L = 100 -70)
//                   (100*100 roll banner) - (70*50 roll banner used) = (30*50 saved in ProductionInventory)


//                        set the leftover materials in productionInventory
//                        productInventory.setLength(initialLength);
//                        productInventory.setWidth(initialWidth);
//                        productInventory.setQuantity(initialQuantity)
//
//                        save the leftover materials in productionInventory
//                        productInventoryService.save(savedProductionInventory);

//                        ProductionInventory activeProductionInventory = ProductionInventory();

//                        active material needed to make the product
//                        activeProductionInventory.setLength(productionInProgress.Length);
//                        activeProductionInventory.setWidth(productionInProgress.Width);
//                        activeProductionInventory.setQuantity(productionInProgress.getQuantity);
//                        productInventoryService.save(activeProductionInventory);

//                        database for active Production Inventory materials
//                        List..... to see all active materials being used



    @GetMapping("/{id}/delete")
        public String deleteStockInventoryById(@PathVariable Long id){

            stockInventoryService.delete(stockInventoryService.findById(id));
            //stockInventoryService.deleteById(id);
            return "redirect:/stockInventory/list";
        }

        // creating a material vs adding stock of existing material
        @GetMapping("/list")
        public String listStockInventory(Model model) {

            Set<StockInventory> stockInventoryList = stockInventoryService.findAll();
            if (stockInventoryList.isEmpty()) {
                return "customer/notFound";
            } else {

                model.addAttribute("selections", stockInventoryList);
                return "stockInventory/stockInventoryList";
            }
        }
}


/*

//    Material LogOut page;
//       1 see available materials in Production Inventory - List all Production Inventory
//       2 or Log material out of Stock,
//            (1) is selected
//                1 list all available materials in Production Inventory (Link each material id to material detail with option to log out)
//                2 Search from materials in Production Inventory(link to details--- logout option)
//            (2) is selected
//                1 list all available materials in Stock Inventory (Link each material id to material detail with option to log out)
//                2 Search from materials in Stock Inventory(link to details--- logout option);

*/

/**** Material from Production is slelected  *****/


/*
    @PostMapping("/new")
    public String processCreationForm(@Valid StockInventory stockInventory,
                                      @RequestParam("material.id") Long materialId,
                                      BindingResult result) {

//            this means select a material and enter quantity and size to enter.
//                    not create a new stack in vetory  idea add to anexisting order if it has the same size
// used edit insted of new
        if (result.hasErrors()) {
            return INSERT_OR_UPDATE_FORM;
        } else {

            StockInventory savedStock = stockInventoryService.saveStockInventory(stockInventory, materialId);

            return "redirect:/stockInventory/" + savedStock.getId();
        }
    }*/


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


/**** Material from Stock is slelected  *****/

//            Logout material from StockInventory
//             selectedStockInventory = new StockInventory();

//            get it from the form
//            put it in productionInventory
//
//            material logged out of Stock to ProductInventory
//            ProductInventory productInventory = selectedStockInventoryService.save(selectedStockInventory);
//            remove it from Stock Inventory  (MAYBE PUT IT IN HISTORY??)
//
//            material needed for the product
//            ProductInventory productionInProgress = new ProductInventory();
//
//            Form > productionInProgress
//
//               deduct the Size of material needed from size of material taken out from StockInventory
//                   initialQuantity = productInventory.getQuantity = productionInProgress.getQuantity;
//                   initialLength(LeftOver)  = productInventory.getLength - productionInProgress.Length;
//                   initialWidth(LeftOver)   = productInventory.getWidth - productionInProgress.Width;
//                   (0L = 100 -70)
//                   (100*100 roll banner) - (70*50 roll banner used) = (30*50 saved in ProductionInventory)


//                        set the leftover materials in productionInventory
//                        productInventory.setLength(initialLength);
//                        productInventory.setWidth(initialWidth);
//                        productInventory.setQuantity(initialQuantity)
//
//                        save the leftover materials in productionInventory
//                        productInventoryService.save(savedProductionInventory);

//                        ProductionInventory activeProductionInventory = ProductionInventory();

//                        active material needed to make the product
//                        activeProductionInventory.setLength(productionInProgress.Length);
//                        activeProductionInventory.setWidth(productionInProgress.Width);
//                        activeProductionInventory.setQuantity(productionInProgress.getQuantity);
//                        productInventoryService.save(activeProductionInventory);

//                        database for active Production Inventory materials
//                        List..... to see all active materials being used
