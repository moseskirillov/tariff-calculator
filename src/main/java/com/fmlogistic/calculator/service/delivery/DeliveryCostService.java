package com.fmlogistic.calculator.service.delivery;

import com.fmlogistic.calculator.model.request.CostRequest;

import java.math.BigDecimal;

/**
 * Сервис расчета базовой стоимости доставки
 */
public interface DeliveryCostService {
    BigDecimal calculate(CostRequest request);
}
