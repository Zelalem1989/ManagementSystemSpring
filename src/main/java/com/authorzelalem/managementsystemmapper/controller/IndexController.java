package com.authorzelalem.managementsystemmapper.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    @RequestMapping({"", "/", "index", "index.html"})
    public String index(){

        return "index";
    }
    @RequestMapping( "customer/index")
    public String customerIndex(){

        return "customer/index";
    }

    @RequestMapping( "customer/orders/index")
    public String orderIndex(){

        return "customer/orders/index";
    }

    @RequestMapping( "material/index")
    public String materialIndex(){

        return "material/index";
    }
    @RequestMapping( "products/index")
    public String productsIndex(){

        return "products/index";
    }
    @RequestMapping( "stockInventory/index")
    public String stockInventoryIndex(){

        return "stockInventory/index";
    }

    @RequestMapping( "productionInventory/index")
    public String productionInventoryIndex(){

        return "productionInventory/index";
    }


    @RequestMapping("/oups")
    public String oupsHandler(){
        return "notimplemented";
    }
}
