package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.dto.CustomerDto;
import com.authorzelalem.managementsystemmapper.mapper.CustomerMapper;
import com.authorzelalem.managementsystemmapper.model.Customer;
import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.model.Orders;
import com.authorzelalem.managementsystemmapper.repositories.CustomerRepositories;
import com.authorzelalem.managementsystemmapper.repositories.OrderItemRepositories;
import com.authorzelalem.managementsystemmapper.repositories.OrderRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepositories customerRepositories;
    private final OrderRepositories orderRepositories;
    private final OrderItemRepositories orderItemRepositories;

    @Override
    @Transactional
    public CustomerDto saveCustomerDto(CustomerDto customerdto) {
        //Customer detachedCustomer = customerMapper.INSTANCE.dtoToModel(customerdto);
        // User user = UserMapper.mapToUser(userDto);
        Customer customer = CustomerMapper.INSTANCE.dtoToModel(customerdto);

        //Customer savedCustomer= customerRepositories.save(detachedCustomer);
        Customer savedUser = customerRepositories.save(customer);

        //UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        CustomerDto savedCustomerDto = CustomerMapper.INSTANCE.modelToDto(savedUser);

        return savedCustomerDto;
    }

    @Override
    public List<Customer> findByName(String name) {

        return customerRepositories.findByName(name);
    }

    @Override
    public List<Customer> findAllByNameLike(String name) {
        return customerRepositories.findAllByNameLike(name);
    }

    @Override
    public Set<Customer> findAll() {
        Set<Customer> customer = new HashSet<>();
        customerRepositories.findAll().forEach(customer::add);
        return customer;
    }

    @Override
    public Customer findById(Long aLong) {
        return customerRepositories.findById(aLong).orElse(null);
    }

    @Override
    public Customer save(Customer object) {
        return customerRepositories.save(object);
    }

    @Override
    public void delete(Customer object) {
        customerRepositories.delete(object);
    }

    @Override
    public void deleteById(Long customerId) {

        //   find all the orders of the customer
        List<Orders> orders = orderRepositories.findOrderProducts(customerId);

        //    find all orderItems of all the orders
        List<OrderItem> orderItems = orderItemRepositories.findOrderProducts(customerId);

        //   delete all orderItems

        orderItems.stream().forEach(orderItemToDelete -> orderItemRepositories.delete(orderItemToDelete));

        //   delete all orders
        orders.stream().forEach(ordersToDelete -> orderRepositories.delete(ordersToDelete));

        // delete customer
        customerRepositories.deleteById(customerId);

        }

    @Override
    public void deleteAllById(Long customerId) {

       //Long idToDelete = customerRepositories.findAll();
//       List<OrderItem> orderItems = orderItemRepositories.findOrderProducts(customerId);
//        customerRepositories.findAll().forEach(orderItems::remove);
//
//        //log.debug("Deleting order: " + customerId + ":" + idToDelete);
//        //System.out.println("Deleting order: " + customerId + ":" + idToDelete);
//        Optional<Customer> customerOptional = customerRepositories.findById(customerId);
//
//        if (customerOptional.isPresent()) {
//            Customer customer = customerOptional.get();
//            log.debug("found Customer");
//            System.out.println("found Customer");
//            Optional<Orders> orderOptional = customer
//                    .getOrders()
//                    .stream()
//                    .filter(orders -> orders.getId().equals(idToDelete))
//                    .findFirst();
//
//            if (orderOptional.isPresent()) {
//                log.debug("found Order");
//                System.out.println("found Order");
//                Orders ordersToDelete = orderOptional.get();
//                ordersToDelete.setCustomers(null);
//                customer.getOrders().remove(orderOptional.get());
//                customerRepositories.save(customer);
//                orderItemRepositories.deleteById(idToDelete);
//                orderRepositories.deleteById(idToDelete);
//
//                Optional<OrderItem> orderItemOptional = ordersToDelete
//                        .getOrderItems()
//                        .stream()
//                        .filter(orders -> orders.getId().equals(idToDelete))
//                        .findFirst();
//
//                if (orderItemOptional.isPresent()) {
//                    log.debug("found orderItem");
//                    System.out.println("found orderItem");
//                    OrderItem orderItemToDelete = orderItemOptional.get();
//                    orderItemToDelete.setOrders(null);
//                    ordersToDelete.getOrderItems().remove(orderItemOptional.get());
//                    // customer.getOrders().remove(orderOptional.get());
//                    // customerRepositories.save(customer);
//                    orderItemRepositories.deleteById(idToDelete);
//                    orderRepositories.deleteById(idToDelete);
//
//                }
//            }
//        } else {
//                log.debug("Customer Id Not found. Id:" + customerId);
//                System.out.println("Customer Id Not found. Id:" + customerId);
//                System.out.println("Order Id Not found. Id:" + idToDelete);
//        }
    }
    @Override
    @Transactional
    public CustomerDto findDtoById(Long aLong) {

        CustomerDto customerDto = CustomerMapper.INSTANCE.modelToDto(findById(aLong));

        return customerDto;
    }
}
// System.out.println("this is order items to be deleted " + orderItems.toString());
//        System.out.println("this is order items to be deleted " + orders.toString());
//        orderItemRepositories.findAll().forEach(orders::remove);
//        orderItemRepositories.findAll().forEach(orders::remove);