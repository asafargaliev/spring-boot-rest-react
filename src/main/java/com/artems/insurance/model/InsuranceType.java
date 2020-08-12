package com.artems.insurance.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "INSURANCE_TYPES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceType extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

}
