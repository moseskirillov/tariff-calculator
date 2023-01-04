package com.fmlogistic.calculator.entity;

import com.fmlogistic.calculator.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "ADDITIONAL_SERVICES")
public class AdditionalService extends BaseEntity {
    @Column(name = "NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "CALCULATION_METHOD_ID")
    private CalculationMethod calculationMethod;
}
