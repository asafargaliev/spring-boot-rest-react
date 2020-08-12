package com.artems.insurance.service;

import com.artems.insurance.model.InsuranceType;
import com.artems.insurance.repository.InsuranceTypeRepository;
import com.artems.insurance.service.impl.InsuranceTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {InsuranceTypeServiceImpl.class})
public class InsuranceTypeServiceTest {

    @Autowired
    InsuranceTypeService insuranceTypeService;

    @MockBean
    InsuranceTypeRepository insuranceTypeRepository;

    @Test
    public void testGetInsuranceTypes() {
        InsuranceType insuranceType1 = new InsuranceType(1L, "Страхование дома");
        InsuranceType insuranceType2 = new InsuranceType(2L, "Страхование авто");

        List<InsuranceType> mockInsuranceTypes = Arrays.asList(insuranceType1, insuranceType2);
        when(insuranceTypeRepository.findAll())
                .thenReturn(mockInsuranceTypes);

        List<InsuranceType> insuranceTypes = insuranceTypeService.getInsuranceTypes();

        assertEquals(2, insuranceTypes.size());
        assertEquals(mockInsuranceTypes, insuranceTypes);
    }

}
