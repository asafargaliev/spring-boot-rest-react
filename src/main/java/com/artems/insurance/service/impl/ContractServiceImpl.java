package com.artems.insurance.service.impl;

import com.artems.insurance.model.Contract;
import com.artems.insurance.repository.ContractRepository;
import com.artems.insurance.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Page<Contract> getContracts(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract getContractById(Long contractId) {
        return contractRepository.findById(contractId)
                .orElseThrow(() -> new EntityNotFoundException("Not found Contract with id = " + contractId));
    }

    @Override
    public Contract updateContract(Long contractId, Contract contract) {
        Contract existingContract = getContractById(contractId);
        existingContract.setRegNumber(contract.getRegNumber());
        existingContract.setInsuranceType(contract.getInsuranceType());
        existingContract.setInsuranceAmount(contract.getInsuranceAmount());
        existingContract.setInsurancePayment(contract.getInsurancePayment());

        Contract updatedContract = contractRepository.save(existingContract);
        return updatedContract;
    }

    @Override
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void deleteContract(Long contractId) {
        Contract existingContract = getContractById(contractId);
        contractRepository.delete(existingContract);
    }
}
