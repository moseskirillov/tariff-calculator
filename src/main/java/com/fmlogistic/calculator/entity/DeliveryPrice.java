package com.fmlogistic.calculator.entity;

import com.fmlogistic.calculator.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "DELIVERY_PRICE")
public class DeliveryPrice extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGREEMENT_ID")
    private Agreement agreement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOODS_TYPE_ID")
    private GoodsType goodsType;
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
    private boolean senderRetail;
    @Column(name = "SENDER_FM")
    private boolean senderFm;
    @Column(name = "CONSIGNEE_CODE")
    private Integer consigneeCode;
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
    private boolean consigneeRetail;
    @Column(name = "CONSIGNEE_FM")
    private boolean consigneeFM;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CALCULATION_METHOD_ID")
    private CalculationMethod calculationMethod;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSPORT_MODE_ID")
    private TransportMode transportMode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VEHICLE_TYPE_ID")
    private VehicleType vehicleType;
    @Column(name = "UNIT_TYPE")
    private String unitType;
    @Column(name = "FROM_QTY")
    private int fromQty;
    @Column(name = "TO_QTY")
    private int toQty;
    @Column(name = "MINIMAL_PRICE")
    private BigDecimal minimalPrice;
    @Column(name = "BASE_PRICE")
    private BigDecimal basePrice;
    @Column(name = "ADD_UNIT_PRICE")
    private BigDecimal addUnitPrice;
    @Column(name = "UNIV_UNIT_PRICE")
    private BigDecimal univUnitPrice;
    @Column(name = "DAY_OF_WEEK")
    private String dayOfWeek;
}
