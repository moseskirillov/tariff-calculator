package com.fmlogistic.calculator.service.insurance;

import com.fmlogistic.calculator.model.request.CostRequest;
import com.fmlogistic.calculator.model.response.InsuranceResponse;

import java.util.List;

/**
 * Сервис расчета стоиомости страховки
 */
public interface InsuranceService {
    List<InsuranceResponse> calculateInsuranceCost(CostRequest request);
}
