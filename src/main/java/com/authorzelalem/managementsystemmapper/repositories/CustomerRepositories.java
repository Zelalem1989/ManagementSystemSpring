package com.authorzelalem.managementsystemmapper.repositories;

import com.authorzelalem.managementsystemmapper.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepositories extends JpaRepository<Customer, Long> {

    @Query("select u from Customer u where u.Name = ?1")
    List<Customer> findByName(String name);
    @Query("select u from Customer u where u.Name = ?1")
    List<Customer> findAllByNameLike(String name);

}
