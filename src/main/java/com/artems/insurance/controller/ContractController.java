package com.artems.insurance.controller;

import com.artems.insurance.dto.ContractDto;
import com.artems.insurance.mapper.ContractMapper;
import com.artems.insurance.model.Contract;
import com.artems.insurance.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService contractService;
    private final ContractMapper contractMapper;

    @Autowired
    public ContractController(ContractService contractService, ContractMapper contractMapper) {
        this.contractService = contractService;
        this.contractMapper = contractMapper;
    }

    @GetMapping
    public Page<ContractDto> getContracts(
            @PageableDefault(sort = { "id" }
            , direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("getContracts called");

        Page<Contract> contracts = contractService.getContracts(pageable);
        Page<ContractDto> contractsDto = contracts.map(x -> contractMapper.toDto(x));
        return contractsDto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable Long id) {
        log.info("getContractById WITH contractId: {}", id);
        return ResponseEntity.ok(contractMapper.toDto(contractService.getContractById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDto> update(@PathVariable Long id, @RequestBody ContractDto contract) {
        Contract updatedContract = contractService.updateContract(id, contractMapper.toEntity(contract));
        if (updatedContract == null) {
            log.error("update WITH contractId: {}, update failed", id);
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
        }

        log.info("update WITH contractId: {}", id);
        return ResponseEntity.ok(contractMapper.toDto(updatedContract));
    }

    @PostMapping
    public ResponseEntity<ContractDto> saveContract(@RequestBody ContractDto contract) {
        Contract savedContract = contractService.saveContract(contractMapper.toEntity(contract));
        if (savedContract == null) {
            log.error("saveContract failed");
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
        }

        log.info("saveContract called");
        return ResponseEntity.ok(contractMapper.toDto(savedContract));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);

        log.info("deleteContract WITH contractId: {}", id);
        return ResponseEntity.noContent().build();
    }


}
