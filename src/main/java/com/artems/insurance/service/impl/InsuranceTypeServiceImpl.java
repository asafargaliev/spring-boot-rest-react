package com.artems.insurance.service.impl;

import com.artems.insurance.model.InsuranceType;
import com.artems.insurance.repository.InsuranceTypeRepository;
import com.artems.insurance.service.InsuranceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceTypeServiceImpl implements InsuranceTypeService {

    private final InsuranceTypeRepository insuranceTypeRepository;

    @Autowired
    public InsuranceTypeServiceImpl(InsuranceTypeRepository insuranceTypeRepository) {
        this.insuranceTypeRepository = insuranceTypeRepository;
    }

    @Override
    public List<InsuranceType> getInsuranceTypes() {
        return insuranceTypeRepository.findAll();
    }

}
