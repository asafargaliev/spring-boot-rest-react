package com.artems.insurance.controller;

import com.artems.insurance.dto.ContractDto;
import com.artems.insurance.dto.InsuranceTypeDto;
import com.artems.insurance.model.Contract;
import com.artems.insurance.model.InsuranceType;
import com.artems.insurance.repository.InsuranceTypeRepository;
import com.artems.insurance.service.ContractService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContractControllerTest {

    @MockBean
    private ContractService contractService;

    @MockBean
    private InsuranceTypeRepository insuranceTypeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void testGetContracts() throws Exception {
        InsuranceType insuranceType = new InsuranceType(1L, "Страхование дома");
        Contract contract1 = new Contract(1L, "1", new BigDecimal(10), new BigDecimal(10), insuranceType);
        Contract contract2 = new Contract(2L, "2", new BigDecimal(10), new BigDecimal(10), insuranceType);

        Page<Contract> mockContracts = new PageImpl(Arrays.asList(contract1, contract2));

        when(contractService.getContracts(Mockito.any(Pageable.class)))
                .thenReturn(mockContracts);

        when(insuranceTypeRepository.findByName(insuranceType.getName()))
                .thenReturn(Optional.of(insuranceType));

        // Execute the GET request
        mockMvc.perform(get("/api/contracts?page=0&size=10"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$['content']", hasSize(2)))
                .andExpect(jsonPath("$['content'][0].id", is(1)))
                .andExpect(jsonPath("$['content'][0].regNumber", is("1")))
                .andExpect(jsonPath("$['content'][0].insuranceAmount", is(10)))
                .andExpect(jsonPath("$['content'][0].insurancePayment", is(10)))
                .andExpect(jsonPath("$['content'][0].insuranceType.name", is("Страхование дома")))

                .andExpect(jsonPath("$['content'][1].id", is(2)))
                .andExpect(jsonPath("$['content'][1].regNumber", is("2")))
                .andExpect(jsonPath("$['content'][1].insuranceAmount", is(10)))
                .andExpect(jsonPath("$['content'][1].insurancePayment", is(10)))
                .andExpect(jsonPath("$['content'][1].insuranceType.name", is("Страхование дома")));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testSaveContract() throws Exception {
        InsuranceType insuranceType = new InsuranceType(1L, "Страхование дома");

        ContractDto contractDto = ContractDto.builder()
                .regNumber("1")
                .insuranceAmount(new BigDecimal(10))
                .insurancePayment(new BigDecimal(10))
                .insuranceType(InsuranceTypeDto.builder().name("Страхование дома").build())
                .build();

        Contract mockContract = Contract.builder()
                .regNumber("1")
                .insuranceAmount(new BigDecimal(10))
                .insurancePayment(new BigDecimal(10))
                .insuranceType(insuranceType)
                .build();

        Contract savedContract = Contract.builder()
                .id(1L)
                .regNumber("1")
                .insuranceAmount(new BigDecimal(10))
                .insurancePayment(new BigDecimal(10))
                .insuranceType(insuranceType)
                .build();

        when(contractService.saveContract(mockContract))
                .thenReturn(savedContract);

        when(insuranceTypeRepository.findByName(insuranceType.getName()))
                .thenReturn(Optional.of(insuranceType));

        // Execute the POST request
        mockMvc.perform(post("/api/contracts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(contractDto)))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.regNumber", is("1")))
                .andExpect(jsonPath("$.insuranceAmount", is(10)))
                .andExpect(jsonPath("$.insurancePayment", is(10)))
                .andExpect(jsonPath("$.insuranceType.name", is("Страхование дома")));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
