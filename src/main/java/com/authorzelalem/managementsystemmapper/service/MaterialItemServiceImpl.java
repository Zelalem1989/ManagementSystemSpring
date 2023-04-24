package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.MaterialItem;
import com.authorzelalem.managementsystemmapper.repositories.MaterialItemRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class MaterialItemServiceImpl implements MaterialItemService {

    private MaterialItemRepositories materialItemRepositories;

    public MaterialItemServiceImpl(MaterialItemRepositories materialItemRepositories) {
        this.materialItemRepositories = materialItemRepositories;
    }

    @Override
    public Set<MaterialItem> findAll() {
        Set<MaterialItem> MaterialItem = new HashSet<>();
        materialItemRepositories.findAll().forEach(MaterialItem::add);
        return MaterialItem;
    }

    @Override
    public MaterialItem findById(Long aLong) { return materialItemRepositories.findById(aLong).orElse(null); }

    @Override
    public MaterialItem save(MaterialItem object) { return materialItemRepositories.save(object); }

    @Override
    public void delete(MaterialItem object) {  materialItemRepositories.delete(object);  }

    @Override
    public void deleteById(Long aLong) {   materialItemRepositories.deleteById(aLong);  }
}
