package com.artems.insurance.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "CONTRACTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String regNumber;

    @Column(nullable = false)
    private BigDecimal insuranceAmount;

    @Column(nullable = false)
    private BigDecimal insurancePayment;

    @ManyToOne
    @JoinColumn(name = "INSURANCE_TYPE_ID", nullable = false)
    private InsuranceType insuranceType;

}
