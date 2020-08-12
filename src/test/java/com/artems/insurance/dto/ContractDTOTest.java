package com.artems.insurance.dto;

import com.artems.insurance.mapper.ContractMapper;
import com.artems.insurance.model.Contract;
import com.artems.insurance.model.InsuranceType;
import com.artems.insurance.repository.InsuranceTypeRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ContractMapper.class, ModelMapper.class})
public class ContractDTOTest {

    @Autowired
    private ContractMapper mapper;

    @MockBean
    InsuranceTypeRepository insuranceTypeRepository;

    @Test
    public void testToDTO() {
        Contract contract = Contract.builder()
                .id(1L)
                .regNumber("1")
                .insurancePayment(new BigDecimal(10))
                .insuranceAmount(new BigDecimal(10))
                .insuranceType(InsuranceType.builder().
                        id(1L)
                        .name("Страхование дома")
                        .build())
                .build();

        ContractDto contractDto = mapper.toDto(contract);
        assertEquals(contract.getId(), contractDto.getId());
        assertEquals(contract.getRegNumber(), contractDto.getRegNumber());
        assertEquals(contract.getInsuranceAmount(), contractDto.getInsuranceAmount());
        assertEquals(contract.getInsurancePayment(), contractDto.getInsurancePayment());
        assertEquals(contract.getInsuranceType().getName(), contractDto.getInsuranceType().getName());
    }

    @Test
    public void testDTOToEntity() {
        when(insuranceTypeRepository.findByName("Страхование дома"))
                .thenReturn(Optional.of(InsuranceType.builder().id(1L).name("Страхование дома").build()));

        ContractDto contractDto = ContractDto.builder()
                .id(1L)
                .regNumber("1")
                .insurancePayment(new BigDecimal(10))
                .insuranceAmount(new BigDecimal(10))
                .insuranceType(InsuranceTypeDto.builder()
                        .name("Страхование дома")
                        .build())
                .build();

        Contract contract = mapper.toEntity(contractDto);

        assertEquals(contract.getId(), contractDto.getId());
        assertEquals(contract.getRegNumber(), contractDto.getRegNumber());
        assertEquals(contract.getInsuranceAmount(), contractDto.getInsuranceAmount());
        assertEquals(contract.getInsurancePayment(), contractDto.getInsurancePayment());
        assertEquals(contract.getInsuranceType().getName(), contractDto.getInsuranceType().getName());
    }

    @Test
    public void testDTOToEntityFail() {
        ContractDto contractDto = ContractDto.builder()
                .id(1L)
                .regNumber("1")
                .insurancePayment(new BigDecimal(10))
                .insuranceAmount(new BigDecimal(10))
                .insuranceType(InsuranceTypeDto.builder()
                        .name("Страхование дома1111")
                        .build())
                .build();

        assertThrows(MappingException.class, () -> mapper.toEntity(contractDto));
    }



}
