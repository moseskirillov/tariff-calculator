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
@Table(name = "CONSIGNEE_LIST")
public class ConsigneeList extends BaseEntity {
    @Column(name = "CITY")
    private String city;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS")
    private String address;
}
