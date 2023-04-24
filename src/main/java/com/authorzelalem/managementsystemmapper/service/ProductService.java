package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.Product;

import java.util.Optional;

public interface ProductService extends  CrudService<Product, Long>{

    Long findOrderProducts(Long id);


}
