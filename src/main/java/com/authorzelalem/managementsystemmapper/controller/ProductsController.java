package com.authorzelalem.managementsystemmapper.controller;


import com.authorzelalem.managementsystemmapper.model.Customer;
import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.Product;
import com.authorzelalem.managementsystemmapper.service.CustomerService;
import com.authorzelalem.managementsystemmapper.service.OrderItemService;
import com.authorzelalem.managementsystemmapper.service.OrderService;
import com.authorzelalem.managementsystemmapper.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RequestMapping("/products")
@Slf4j
@Controller
@AllArgsConstructor
public class ProductsController {


    private static final String CREATE_OR_UPDATE_FORM = "products/createOrUpdateProductsForm";
    private final CustomerService customerService;
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final ProductService productService;


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{id}/edit")
    public String initUpdateProductForm(@PathVariable Long id, Model model) {
        model.addAttribute(productService.findById(id));
        return CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{id}/edit")
    public String processUpdateProductForm(@Valid Product product, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_FORM;
        } else {
            product.setId(id);
            Product savedProduct = productService.save(product);
            return "redirect:/products/" + savedProduct.getId();
        }
    }

    @GetMapping("/delete")
    public String deleteProduct(@PathVariable Long id) {

        //log.debug("Deleting id: " + id);
        productService.deleteById(id);

        return "redirect:/products/index";
    }

    @GetMapping("/{id}/delete")
    public String deleteProductById(@PathVariable Long id) {

        //log.debug("Deleting id: " + id);
        productService.deleteById(id);

        return "redirect:/products/index";
    }

    @GetMapping("/list")
    public String listProducts(Model model) {

        Set<Product> productsList = productService.findAll();

        if (productsList.isEmpty()) {
            return "customer/notFound";

        } else {
            model.addAttribute("productsList", productsList);
            return "products/productList";
        }
    }

    @GetMapping
    @RequestMapping("/new")
    public String initCreationForm(Model model) {

        model.addAttribute("product", new Product());

        return CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_FORM;
        } else {
            Product savedProduct = productService.save(product);
            return "redirect:/products/" + savedProduct.getId();

        }
    }

    @GetMapping
    @RequestMapping("/{id}")
    public String showProducts(@PathVariable Long id, Model model) {

        Product results = productService.findById(id);

        model.addAttribute("product", results);

        return "/products/productDetails";

    }
}
