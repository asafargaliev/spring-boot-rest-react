package com.artems.insurance.repository;

import com.artems.insurance.model.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Long> {

    Optional<InsuranceType> findByName(String name);

}
