package com.artems.insurance.controller;

import com.artems.insurance.dto.InsuranceTypeDto;
import com.artems.insurance.service.InsuranceTypeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/api/types")
@Slf4j
public class InsuranceTypesController {

    private final InsuranceTypeService insuranceTypeService;
    private final ModelMapper mapper;

    @Autowired
    public InsuranceTypesController(InsuranceTypeService insuranceTypeServiceDTO, ModelMapper mapper) {
        this.insuranceTypeService = insuranceTypeServiceDTO;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<InsuranceTypeDto> getTypes() {
        log.info("getTypes called");
        return insuranceTypeService.getInsuranceTypes()
                .stream()
                .map(x -> mapper.map(x, InsuranceTypeDto.class))
                .collect(Collectors.toList());
    }

}
