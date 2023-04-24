package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.Customer;
import com.authorzelalem.managementsystemmapper.model.Orders;
import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.ProductionInventory;
import com.authorzelalem.managementsystemmapper.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// problem with the database creation it has 2 id and 2cx id ????
@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerRepositories customerRepositories;
    private final OrderRepositories orderRepositories;
    private final OrderItemRepositories orderItemRepositories;
    private final ProductsRepositories productsRepositories;




    @Override
    @Transactional
    public Orders saveOrderDto(Orders orders, OrderItem orderItem, Long ProductId) {

        // Find the Customer
        Optional<Customer> customerOptional = customerRepositories.findById(orders.getCustomers().getId());
        System.out.println("customer Id in SaveOrderDto: " + orders.getCustomers().getId());

        if (!customerOptional.isPresent()) {

            return new Orders();
        } else {
            // dose orderItem have all the attribute do i need the size quantity....
            //Orders SavedOrder = orderRepositories.save(orders);
           // OrderItem savedOrderItem = orderItemRepositories.save(orderItem);

            orderRepositories.save(orders);
            // get the customer
            System.out.println("customer ID" + orders.getCustomers().getId());
            System.out.println("order ID " + orders.getCustomers().getId());

            Customer customer = customerOptional.get();
            // why set it again
            orders.setCustomers(customer);

            String Size = orderItem.getLength() + "X" + orderItem.getWidth();

            orderItem.setSize(Size);

            orderItem.setProduct(productsRepositories.getById(ProductId));

            orders.addOrderItems(orderItem);

            customer.addOrder(orders);

            customerRepositories.save(customer);

            orderRepositories.save(orders);

            return orders;
        }
    }
    @Override
    public void deleteById(Long customerId, Long idToDelete) {

        log.debug("Deleting order: " + customerId + ":" + idToDelete);
        System.out.println("Deleting order: " + customerId + ":" + idToDelete);
        Optional<Customer> customerOptional = customerRepositories.findById(customerId);

        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            log.debug("found Customer");
            System.out.println("found Customer");
            Optional<Orders> orderOptional = customer
                    .getOrders()
                    .stream()
                    .filter(orders -> orders.getId().equals(idToDelete))
                    .findFirst();

            if(orderOptional.isPresent()){
                log.debug("found Order");
                System.out.println("found Order");
                Orders ordersToDelete = orderOptional.get();
                ordersToDelete.setCustomers(null);
                customer.getOrders().remove(orderOptional.get());
                customerRepositories.save(customer);
                orderItemRepositories.deleteById(idToDelete);
                orderRepositories.deleteById(idToDelete);

            }
        } else {
            log.debug("Customer Id Not found. Id:" + customerId);
            System.out.println("Customer Id Not found. Id:" + customerId);
        }
    }

    @Override
    public List<Orders> findOrderProducts(Long customerId) {
        return orderRepositories.findOrderProducts(customerId);
    }

    @Override
    public Set<Orders> findAll() {
        Set<Orders> orders = new HashSet<>();
        orderRepositories.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Orders addOrderMaterial(Customer customer, ProductionInventory productionInventory, Orders orders, OrderItem orderItem, Long product_id) {

//                find the product
//                    add material to the product from product inventory;
//                        if material is not in productionInventory add it from stock

        //orderItem.setProductionInventory(productionInventory);

        //orderItem.addProductionInventory(productionInventory);

        orderItem.setProductionInventory(productionInventory);

        orders.addOrderItems(orderItem);

        customer.addOrder(orders);

        customerRepositories.save(customer);

        //Orders SavedOrder = orderRepositories.save(orders);

        orderRepositories.save(orders);

        return orders;
    }
    @Override
    public Orders findById(Long aLong) {
        return orderRepositories.findById(aLong).orElse(null);
    }

    @Override
    public Orders save(Orders object) {
        return orderRepositories.save(object);
    }

    @Override
    public void delete(Orders object) {
        orderRepositories.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepositories.deleteById(aLong);
    }
}