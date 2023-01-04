package com.fmlogistic.calculator.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Доп DTO для расширения ответа по страховкам
 */
@Data
@AllArgsConstructor
public class InsuranceResponse {
    private String name;
    private BigDecimal cost;
    private double percent;
}