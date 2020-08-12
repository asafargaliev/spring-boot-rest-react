package com.artems.insurance.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {

    private Long id;
    private String regNumber;
    private BigDecimal insuranceAmount;
    private BigDecimal insurancePayment;
    private InsuranceTypeDto insuranceType;

}
