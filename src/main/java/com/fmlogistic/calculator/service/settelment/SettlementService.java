package com.fmlogistic.calculator.service.settelment;

import com.fmlogistic.calculator.model.request.CostRequest;
import com.fmlogistic.calculator.model.response.CostResponse;

/**
 * Главный сервис для получения полной стоиомости всех услуг
 */
public interface SettlementService {
    CostResponse calculate(CostRequest request);
}
