package com.artems.insurance.mapper;

import com.artems.insurance.dto.ContractDto;
import com.artems.insurance.model.Contract;
import com.artems.insurance.repository.InsuranceTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

@Component
@Slf4j
public class ContractMapper extends AbstractMapper<Contract, ContractDto> {

    private final InsuranceTypeRepository insuranceTypeRepository;

    @Autowired
    public ContractMapper(InsuranceTypeRepository insuranceTypeRepository) {
        super(Contract.class, ContractDto.class);
        this.insuranceTypeRepository = insuranceTypeRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ContractDto.class, Contract.class)
                .addMappings(m -> m.skip(Contract::setInsuranceType))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Contract source, ContractDto destination) {
    }

    @Override
    void mapSpecificFields(ContractDto source, Contract destination) {
        destination.setInsuranceType(insuranceTypeRepository.findByName(source.getInsuranceType().getName())
                .orElseThrow(() ->  {
                    log.error("Error mapping ContractDTO to Contract entity: not found InsuranceType with name '{}'", source.getInsuranceType().getName());
                    return new EntityNotFoundException("Not found InsuranceType with name '" + source.getInsuranceType().getName() + "'");
                }));
    }
}
