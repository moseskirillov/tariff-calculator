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
@Table(name = "CLIENTS")
public class Client extends BaseEntity {
    @Column(name = "NAME")
    private String name;
    @Column(name = "SCORE")
    private Float score;
}
