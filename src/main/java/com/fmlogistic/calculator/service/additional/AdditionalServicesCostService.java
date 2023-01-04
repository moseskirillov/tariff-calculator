package com.fmlogistic.calculator.service.additional;

import com.fmlogistic.calculator.model.request.CostRequest;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Сервис расчета стомости дополнительных услуг
 */
public interface AdditionalServicesCostService {
    Map<String, BigDecimal> calculateAdditionalServicesCost(CostRequest request, String unitType);
}