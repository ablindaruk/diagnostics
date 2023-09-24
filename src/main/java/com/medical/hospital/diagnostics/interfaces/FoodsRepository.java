package com.medical.hospital.diagnostics.interfaces;

import com.medical.hospital.diagnostics.medicineresources.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodsRepository extends JpaRepository<Food, Integer> {
    Optional<Food> findById(Integer id);
}
