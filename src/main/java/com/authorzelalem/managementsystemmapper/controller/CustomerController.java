package com.authorzelalem.managementsystemmapper.controller;

import com.authorzelalem.managementsystemmapper.model.Customer;
import com.authorzelalem.managementsystemmapper.service.CustomerService;
import com.authorzelalem.managementsystemmapper.service.OrderItemService;
import com.authorzelalem.managementsystemmapper.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@RequestMapping("/customer")
@Slf4j
@Controller
@AllArgsConstructor
public class CustomerController {
    private static final String VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM = "customer/createOrUpdateCustomerForm";
    private final CustomerService customerService;
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findCustomer(Model model){
        model.addAttribute("customer", Customer.builder().build());
        return "customer/findCustomer";
    }

    @GetMapping
    public String processFindForm(Customer customer, BindingResult result, Model model){

        // allow parameterless GET request for /customer to return all records
        if (customer.getName() == null){
            customer.setName(""); // empty string signifies broadest possible search
        }
        // find Customer by last name
        List<Customer> results = customerService.findByName(customer.getName());

         if (results.isEmpty()) {
            // no Customer found
            result.rejectValue("name", "notFound", "not found");
            return "customer/findCustomer";
        } else if (results.size() == 1) {
            // 1 Customer found
            customer = results.get(0);
            return "redirect:/customer/" + customer.getId();
        } else {
            // multiple customer found
            model.addAttribute("selections", results);
            return "customer/customerList";
        }

    }

    @GetMapping
    @RequestMapping("/{id}")
    public ModelAndView showCustomer(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("customer/customerDetails");
        mav.addObject(customerService.findById(id));
        return mav;
    }

    @GetMapping
    @RequestMapping("/new")
    public String initCreationForm(Model model) {
       // model.addAttribute("customer", Customer.builder().build());
        model.addAttribute("customer", new Customer());

        return VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM;
        } else {
            Customer savedCustomer =  customerService.save(customer);
            return "redirect:/customer/" + savedCustomer.getId();

        }
    }
    @GetMapping("/{id}/edit")
    public String initUpdateCustomerForm(@PathVariable Long id, Model model ) {
        model.addAttribute(customerService.findById(id));
        return VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/{id}/edit")
    public String processUpdateCustomerForm(@Valid Customer customer, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM;
        } else {
            customer.setId(id);
            Customer savedCustomer = customerService.save(customer);
            return "redirect:/customer/" + savedCustomer.getId();
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomerById(@PathVariable Long id){

        //log.debug("Deleting id: " + id);
            customerService.deleteById(id);

        return "redirect:/customer/index";
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {

        Set<Customer> customerList = customerService.findAll();
        if (customerList.isEmpty()) {

           return "customer/notFound";

        } else {
            // List all Customers in database
            model.addAttribute("selections", customerList);
            return "customer/customerList";
        }
    }
}