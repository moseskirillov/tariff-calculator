package com.fmlogistic.calculator.model.request;

import com.fmlogistic.calculator.model.additional.AdditionalService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Запрос на расчет стоиости
 */
@Data
@Setter
@Getter
public class CostRequest {
    /**
     * Тип
     */
    private String tariffType;
    private String transportMode;
    private String goodsType;
    private String vehicleType;
    private int unitQty;
    private boolean senderFm;
    private String senderCity;
    private String senderZone;
    private String consigneeCode;
    private String consigneeCity;
    private String consigneeZone;
    private boolean cargoInsurance;
    private boolean delayInsurance;
    private double cargoCost;
    private List<AdditionalService> additionalServices;
}

