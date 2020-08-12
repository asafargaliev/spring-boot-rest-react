package com.artems.insurance.service;

import com.artems.insurance.model.Contract;
import com.artems.insurance.model.InsuranceType;
import com.artems.insurance.repository.ContractRepository;
import com.artems.insurance.service.impl.ContractServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ContractServiceImpl.class})
public class ContractServiceTest {

    @Autowired
    private ContractService contractService;

    @MockBean
    ContractRepository contractRepository;

    @Test
    public void testGetAllContracts() {
        InsuranceType insuranceType = new InsuranceType(1L, "Страхование дома");

        Contract contract1 = new Contract(1L, "1", new BigDecimal(10), new BigDecimal(10), insuranceType);
        Contract contract2 = new Contract(2L, "2", new BigDecimal(10), new BigDecimal(10), insuranceType);

        List<Contract> mockContracts = Arrays.asList(contract1, contract2);

        when(contractRepository.findAll())
                .thenReturn(mockContracts);

        List<Contract> contracts = contractService.getAllContracts();

        assertEquals(2, contracts.size(), "findAll should return 2 contracts");
        assertEquals(contracts, mockContracts);
    }

    @Test
    public void testGetContractsPageable() {
        InsuranceType insuranceType = new InsuranceType(1L, "Страхование дома");

        Contract contract1 = new Contract(1L, "1", new BigDecimal(10), new BigDecimal(10), insuranceType);
        Contract contract2 = new Contract(2L, "2", new BigDecimal(10), new BigDecimal(10), insuranceType);

        Page<Contract> mockContracts = new PageImpl(Arrays.asList(contract1, contract2));

        Pageable pageable = PageRequest.of(0,10);
        when(contractRepository.findAll(pageable))
                .thenReturn(mockContracts);

        List<Contract> contracts = contractService.getContracts(pageable).getContent();

        assertEquals(2, contracts.size(), "findAll should return 2 contracts");
        assertEquals(contracts, mockContracts.getContent());
    }

    @Test
    public void testGetContractById() {
        InsuranceType insuranceType = new InsuranceType(1L, "Страхование дома");
        Contract contract = new Contract(1L, "1", new BigDecimal(10), new BigDecimal(10), insuranceType);

        when(contractRepository.findById(1L))
                .thenReturn(Optional.of(contract));

        assertEquals(contract, contractService.getContractById(1L));
        assertThrows(EntityNotFoundException.class, () -> contractService.getContractById(2L));
    }

    @Test
    public void testUpdateContract() {
        long contractId = 1L;

        InsuranceType insuranceType = new InsuranceType(1L, "Страхование дома");

        Contract contract = new Contract(contractId,"1", new BigDecimal(10), new BigDecimal(10), insuranceType);
        Contract updatedContract = new Contract(contractId,"2", new BigDecimal(10), new BigDecimal(10), insuranceType);

        when(contractRepository.findById(contractId))
                .thenReturn(Optional.of(contract));

        when(contractRepository.save(updatedContract))
                .thenReturn(updatedContract);

        assertEquals(updatedContract, contractService.updateContract(contractId, updatedContract));
        assertThrows(EntityNotFoundException.class, () -> contractService.updateContract(2L, updatedContract));
    }

    @Test
    public void testSaveContract() {
        InsuranceType insuranceType = new InsuranceType(1L, "Страхование дома");

        Contract savedContract = new Contract(1L,"1", new BigDecimal(10), new BigDecimal(10), insuranceType);

        Contract newContract = Contract.builder()
                .insuranceAmount(new BigDecimal(10))
                .insurancePayment(new BigDecimal(10))
                .regNumber("1")
                .insuranceType(insuranceType)
                .build();

        when(contractRepository.save(newContract))
                .thenReturn(savedContract);

        assertEquals(savedContract, contractService.saveContract(newContract));
    }

    @Test
    public void testDeleteContract() {
        InsuranceType insuranceType = new InsuranceType(1L, "Страхование дома");

        Contract contract = new Contract(1L, "1", new BigDecimal(10), new BigDecimal(10), insuranceType);

        when(contractRepository.findById(1L))
                .thenReturn(Optional.of(contract));

        assertDoesNotThrow(() -> contractService.deleteContract(1L));
    }

}
