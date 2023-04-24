package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.Material;
import com.authorzelalem.managementsystemmapper.model.OrderItem;
import com.authorzelalem.managementsystemmapper.repositories.OrderItemRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepositories orderItemRepositories;

    public OrderItemServiceImpl(OrderItemRepositories orderItemRepositories) {
        this.orderItemRepositories = orderItemRepositories;
    }

    @Override
    public Set<OrderItem> findAll() {
        Set<OrderItem> orderItem = new HashSet<>();
        orderItemRepositories.findAll().forEach(orderItem::add);
        return orderItem;
    }

    @Override
    public OrderItem findById(Long aLong) {
        return orderItemRepositories.findById(aLong).orElse(null);
    }

    @Override
    public OrderItem save(OrderItem object) {
        return orderItemRepositories.save(object);
    }

    @Override
    public void delete(OrderItem object) {
        orderItemRepositories.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {

        orderItemRepositories.deleteById(aLong);
    }

    @Override
    public OrderItem findOrderItems(Long customerId, Long orderId) {
        return orderItemRepositories.findOrderItems(customerId, orderId);
    }

    @Override
    public List<OrderItem> findOrderProducts(Long customerId) {
         return orderItemRepositories.findOrderProducts(customerId);
    }
}
