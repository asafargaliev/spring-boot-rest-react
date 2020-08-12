package com.artems.insurance.service;

import com.artems.insurance.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractService {

    Page<Contract> getContracts(Pageable pageable);

    List<Contract> getAllContracts();

    Contract getContractById(Long contractId);

    Contract updateContract(Long contractId, Contract contract);

    Contract saveContract(Contract contract);

    void deleteContract(Long contractId);

}
