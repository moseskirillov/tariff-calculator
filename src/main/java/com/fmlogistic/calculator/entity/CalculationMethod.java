package com.fmlogistic.calculator.entity;

import com.fmlogistic.calculator.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "CALCULATION_METHODS")
public class CalculationMethod extends BaseEntity {
    @Column(name = "NAME")
    private String name;
    @Column(name = "TECHNICAL_DESCRIPTION")
    private String technicalDescription;
    @Column(name = "APPLICABLE_TO")
    private String applicableTo;
}