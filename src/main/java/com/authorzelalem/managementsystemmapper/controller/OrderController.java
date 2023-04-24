package com.authorzelalem.managementsystemmapper.controller;


import com.authorzelalem.managementsystemmapper.dto.CustomerDto;
import com.authorzelalem.managementsystemmapper.dto.OrderDto;
import com.authorzelalem.managementsystemmapper.mapper.CustomerMapper;
import com.authorzelalem.managementsystemmapper.mapper.OrderMapper;
import com.authorzelalem.managementsystemmapper.model.*;
import com.authorzelalem.managementsystemmapper.service.*;
import jakarta.persistence.criteria.Order;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Controller
@ControllerAdvice
@AllArgsConstructor
public class OrderController {

    private static final String ORDER_CREATE_OR_UPDATE_FORM = "customer/orders/orderForm";
    private static final String MATERIAL_ADD_FORM = "customer/orders/materialAddForm";//AddMaterialToOrder";

    @Autowired
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ProductService productService;

    private final OrderItemService orderItemService;
    private final MaterialService materialService;
    private final MaterialItemService materialItemService;
    private final ProductionInventoryService productionInventoryService;


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping("customer/{customerId}/orders/new")
    public String newCustomerOrder(@PathVariable Long customerId, Model model) {

        OrderItem orderItem = new OrderItem();
        model.addAttribute("orderItem", orderItem);
        model.addAttribute("product", productService.findAll());

        return ORDER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("customer/{customerId}/orders/new")
    public String saveOrUpdate(@ModelAttribute("orderItem") OrderItem orderItem,
                               @RequestParam("product.id") Long product_id,
                               @PathVariable Long customerId,
                               BindingResult result) {

        if (result.hasErrors()) {
            return ORDER_CREATE_OR_UPDATE_FORM;
        } else {

            Orders orders = new Orders();
           // orderService.save(orders);
            orders.setCustomers(customerService.findById(customerId));
            System.out.println("inside Post controller inside ordres after being set  " + orders.getCustomers().getId());
            Orders savedOrder = orderService.saveOrderDto(orders, orderItem, product_id);

            return "redirect:/customer/" + savedOrder.getCustomers().getId() + "/orders/" + savedOrder.getId() + "/show";
        }
    }

    @GetMapping
    @RequestMapping("customer/{customerId}/orders/{id}/show")
    public String showCustomerOrders(@PathVariable Long customerId,
                                     @PathVariable Long id, Model model) {

        OrderItem results = orderItemService.findOrderItems(customerId, id);
        System.out.println("order id " + id);
        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        model.addAttribute("OrderItem", results);

        return "/customer/orders/show";
    }


    @GetMapping("customer/{customerId}/orders")
    public String listCustomerOrders(@PathVariable Long customerId, Model model) {

        Set<Orders> ordersList = orderService.findAll();
        model.addAttribute("selections", ordersList);
        model.addAttribute("customerId", customerId);
        List<OrderItem> results = orderItemService.findOrderProducts(customerId);
        model.addAttribute("orderItems", results);

        return "customer/orders/orderList";
    }

    @GetMapping("/customer/orders")
    public String listOrders(Model model) {

        Set<OrderItem> ordersList = orderItemService.findAll();

        if (ordersList.isEmpty()) {

            return "customer/notFound";

        } else {

            System.out.println("list of all orders " + ordersList.toString());
            model.addAttribute("orderItems", ordersList);

            return "customer/orders/orderList";
        }
    }

    @GetMapping("customer/{customerId}/orders/{id}/delete")
    public String deleteOrder(@PathVariable Long customerId, @PathVariable Long id) {

        log.debug("deleting Order id:" + id);
        orderService.deleteById(customerId, id);

        return "redirect:/customer/" + customerId + "/orders";
    }
    @GetMapping("customer/{customerId}/orders/{id}/add")
    public String initAddMaterial(@PathVariable Long customerId, @PathVariable Long id,Model model) {


        OrderItem orderItem = orderItemService.findOrderItems(customerId, id);
        System.out.println("order Item or the order  " + orderItem.getId());
        Product product = productService.findById(orderItem.getProduct().getId());
        System.out.println("Product in orderItem  " + orderItem.getProduct().getId());
        Set<ProductionInventory> productionInventories = productionInventoryService.findAll();
        System.out.println("Product Inventories  " + productionInventories.toString());
        model.addAttribute("orderItem", orderItem);
        model.addAttribute("product", product);
        model.addAttribute("productionInventory", productionInventories);

        return MATERIAL_ADD_FORM;
    }

    @PostMapping("customer/{customerId}/orders/{id}/add")
    public String addMaterial( @ModelAttribute("productionInventory") ProductionInventory productionInventory,
                              // @ModelAttribute("orderItem") ProductionInventory orderItem,
                               @PathVariable Long id,
                               @PathVariable Long customerId,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            return MATERIAL_ADD_FORM;
        } else {

            Customer customer = customerService.findById(customerId);
            Orders orders = orderService.findById(id);
            OrderItem orderItem = orderItemService.findOrderItems(customerId, id);
            Product product = productService.findById(orderItem.getProduct().getId());

            //savedOrder.setCustomers(customerService.findById(customerId));
            orderItem.setId(id);
            Orders savedOrder = orderService.addOrderMaterial(customer, productionInventory, orders, orderItem, product.getId());

            System.out.println(" After AddMaterial Customer  " + savedOrder.getCustomers().getId());
            System.out.println(" After AddMaterial orders ID  " + savedOrder.getId());
            System.out.println(" After AddMaterial ordersItem from orders   " + savedOrder.getOrderItems().toString());
            System.out.println(" After AddMaterial ordersItem from orders   " + orderItem.getId());
            System.out.println(" After AddMaterial Products from orderItem " + orderItem.getProduct().getId());

            return "redirect:/customer/" + customerId + "/orders/" + id + "/show";

        }
    }

    @GetMapping("customer/{customerId}/orders/{id}/edit")
    public String updateCustomerOrder(@PathVariable Long customerId,
                                      @PathVariable Long id, Model model) {

        model.addAttribute("product", productService.findAll());

        model.addAttribute("orderItem", orderItemService.findById(id));

        return ORDER_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("customer/{customerId}/orders/{id}/edit")
    public String processUpdateCustomerForm(@Valid Orders orders,
                                            @Valid OrderItem orderItem,
                                            BindingResult result,
                                            @PathVariable Long customerId,
                                            @PathVariable Long id) {
        if (result.hasErrors()) {
            return ORDER_CREATE_OR_UPDATE_FORM;
        } else {
            orderItem.setId(id);
            OrderItem savedOrderItem = orderItemService.save(orderItem);
            return "redirect:/customer/" + customerId + "/orders/" + id + "/show";
        }
    }
}
