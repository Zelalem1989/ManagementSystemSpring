
/*
package com.authorzelalem.managementsystemmapper.controller;

import com.authorzelalem.managementsystemmapper.model.Customer;
import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.Orders;
import com.authorzelalem.managementsystemmapper.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


    @Slf4j
    @Controller
    @ControllerAdvice
    @AllArgsConstructor
    public class OrderMaterialsController {

        private static final String ORDER_CREATE_OR_UPDATE_FORM = "customer/orderMaterial/AddMaterialToOrder";

        @Autowired
        private final CustomerService customerService;
        private final OrderService orderService;
        private final ProductService productService;

        private final ProductionInventoryService productionInventoryService;

        private final OrderItemService orderItemService;
        private final MaterialService materialService;
        private final MaterialItemService materialItemService;


        @InitBinder
        public void setAllowedFields(WebDataBinder dataBinder) {
            dataBinder.setDisallowedFields("id");
        }


//        @GetMapping
//        @RequestMapping("customer/{customerId}/orders/{id}/add")
//        public String newCustomer(@PathVariable Long customerId, @PathVariable Long id,Model model) {
//
//            Customer customers = customerService.findById(customerId);
//
//            Orders orders = orderService.findById(id);
//            orders.setCustomers(customers);
//            model.addAttribute("orders", orders);
//            OrderItem orderItem = new OrderItem();
//            model.addAttribute("orderItem", orderItem);
//            model.addAttribute("productionInventory", productionInventoryService.findAll());
//
//            return ORDER_CREATE_OR_UPDATE_FORM;
//        }
//
//        @GetMapping
//        @RequestMapping("customer/{customerId}/orders/{id}/add")
//        public String saveOrUpdate(@ModelAttribute("orders") Orders orders,
//                                   @ModelAttribute("orderItem") OrderItem orderItem,
//                                   @RequestParam("product.id") Long product_id,
//                                   BindingResult result, Model model) {
//
//            if (result.hasErrors()) {
//                return ORDER_CREATE_OR_UPDATE_FORM;
//            } else {
//
////            String Size = orderItem.getLength() + "X" + orderItem.getWidth();
////            orderItem.setSize(Size);
//
//                Orders savedOrder = orderService.saveOrderDto(orders, orderItem, product_id);
//
//                return "redirect:/customer/" + savedOrder.getCustomers().getId() + "/orders/" + savedOrder.getId() + "/show";
//
//            }
//        }

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
*/