package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.Product;
import com.authorzelalem.managementsystemmapper.repositories.ProductsRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepositories productsRepositories;


    @Override
    public Long findOrderProducts(Long id) {
       Long ProductId =  productsRepositories.findOrderProducts(id);
       return ProductId;
    }

    @Override
        public Set<Product> findAll() {
            Set<Product> product = new HashSet<>();
                productsRepositories.findAll().forEach(product::add);
            return product;
        }

        @Override
        public Product findById (Long aLong){
            return productsRepositories.findById(aLong).orElse(null);
        }

        @Override
        public Product save (Product object){
            return productsRepositories.save(object);
        }

        @Override
        public void delete (Product object){
            productsRepositories.delete(object);
        }

        @Override
        public void deleteById (Long aLong){
            productsRepositories.deleteById(aLong);
        }
}
