package com.authorzelalem.managementsystemmapper.service;

import com.authorzelalem.managementsystemmapper.model.Material;
import com.authorzelalem.managementsystemmapper.repositories.MaterialsRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialsRepositories materialsRepositories;

    public MaterialServiceImpl(MaterialsRepositories materialsRepositories) {
        this.materialsRepositories = materialsRepositories;
    }

    @Override
    public Set<Material> findAll() {
        Set<Material> materials = new HashSet<>();
        materialsRepositories.findAll().forEach(materials::add);
        return materials;
    }
    @Override
    public Material findById(Long aLong) {
        return materialsRepositories.findById(aLong).orElse(null);
    }

    @Override
    public Material save(Material object) {
        return materialsRepositories.save(object);
    }

    @Override
    public void delete(Material object) {
        materialsRepositories.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        materialsRepositories.deleteById(aLong);
    }
}
