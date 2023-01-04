package com.fmlogistic.calculator.entity;

import com.fmlogistic.calculator.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "ADDITIONAL_SERVICES_PRICE")
public class AdditionalServicesPrice extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "AGREEMENT_ID")
    private Agreement agreement;
    @ManyToOne
    @JoinColumn(name = "GOODS_TYPE_ID")
    private GoodsType goodsType;
    @ManyToOne
    @JoinColumn(name = "ADDITIONAL_SERVICE_ID")
    private AdditionalService additionalService;
    @Column(name = "SENDER_CODE")
    private String senderCode;
    @Column(name = "SENDER_INDEX_FROM")
    private String senderIndexFrom;
    @Column(name = "SENDER_INDEX_TO")
    private String senderIndexTo;
    @Column(name = "SENDER_ZONE")
    private String senderZone;
    @Column(name = "SENDER_CITY")
    private String senderCity;
    @Column(name = "SENDER_REGION")
    private String senderRegion;
    @Column(name = "SENDER_RETAIL")
    private Boolean senderRetail;
    @Column(name = "SENDER_FM")
    private Boolean senderFm;
    @Column(name = "CONSIGNEE_CODE")
    private String consigneeCode;
    @Column(name = "CONSIGNEE_INDEX_FROM")
    private String consigneeIndexFrom;
    @Column(name = "CONSIGNEE_INDEX_TO")
    private String consigneeIndexTo;
    @Column(name = "CONSIGNEE_ZONE")
    private String consigneeZone;
    @Column(name = "CONSIGNEE_CITY")
    private String consigneeCity;
    @Column(name = "CONSIGNEE_REGION")
    private String consigneeRegion;
    @Column(name = "CONSIGNEE_RETAIL")
    private Boolean consigneeRetail;
    @Column(name = "CONSIGNEE_FM")
    private Boolean consigneeFm;
    @Column(name = "UNIT_TYPE")
    private String unitType;
    @Column(name = "UNIT_QTY")
    private BigDecimal unitQty;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "PERCENT")
    private BigDecimal percent;
}
