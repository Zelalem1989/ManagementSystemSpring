package com.authorzelalem.managementsystemmapper.repositories;

import com.authorzelalem.managementsystemmapper.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialsRepositories extends JpaRepository<Material, Long> {
}
