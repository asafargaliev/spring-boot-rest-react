package com.artems.insurance.dto;

import com.artems.insurance.model.InsuranceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ModelMapper.class})
public class InsuranceTypeDTOTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testToDTO() {
        InsuranceType insuranceType = InsuranceType.builder()
                .id(1L)
                .name("Страхование дома")
                .build();

        InsuranceTypeDto insuranceTypeDto = modelMapper.map(insuranceType, InsuranceTypeDto.class);
        assertEquals(insuranceType.getName(), insuranceTypeDto.getName());
    }

    @Test
    public void testDTOToEntity() {
        InsuranceTypeDto insuranceTypeDto = InsuranceTypeDto.builder()
                .name("Страхование дома")
                .build();

        InsuranceType insuranceType = modelMapper.map(insuranceTypeDto, InsuranceType.class);
        assertEquals(insuranceTypeDto.getName(), insuranceType.getName());
    }

}
