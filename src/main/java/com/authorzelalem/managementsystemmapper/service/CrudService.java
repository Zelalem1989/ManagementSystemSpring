package com.authorzelalem.managementsystemmapper.service;

import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CrudService <T, ID> {
    Set<T> findAll();
    T findById(ID id);
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}
