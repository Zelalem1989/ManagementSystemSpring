package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.dto.CustomerDto;
import com.authorzelalem.managementsystemmapper.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService extends CrudService<Customer, Long> {

    List<Customer> findByName(String name);

    List<Customer> findAllByNameLike(String Name);

    CustomerDto findDtoById(Long aLong);

    CustomerDto saveCustomerDto(CustomerDto customerdto);

    void deleteAllById(Long customerId);

}
