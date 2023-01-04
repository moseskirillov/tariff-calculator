package com.fmlogistic.calculator.service.insurance;

import com.fmlogistic.calculator.exception.CalculateException;
import com.fmlogistic.calculator.model.request.CostRequest;
import com.fmlogistic.calculator.model.response.InsuranceResponse;
import com.fmlogistic.calculator.repository.AdditionalServicesPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private static final String CARGO_INSURANCE_NAME = "Страхование груза";
    private static final String DELAY_INSURANCE_NAME = "Страхование сроков";

    private static final String INSURANCE_COST_CALCULATION_ERROR =
            "Ошибка при расчете стоимости страховки. Пожалуйста, проверьте параметры запроса";

    private final AdditionalServicesPriceRepository additionalServicesPriceRepository;

    /**
     * Расчет стоиомости страховки
     * @param request запрос
     * @return модель стомости страховки и процент
     */
    @Override
    public List<InsuranceResponse> calculateInsuranceCost(CostRequest request) {
        var consigneeCode = additionalServicesPriceRepository.findConsigneeCode(request.getConsigneeCode());
        log.info("Получены значения поля \"consigneeCode\": {}", consigneeCode);
        if (request.getCargoCost() == 0 && (request.isCargoInsurance() || request.isDelayInsurance())) {
            throw new CalculateException(INSURANCE_COST_CALCULATION_ERROR);
        }
        var insuranceCostList = new ArrayList<InsuranceResponse>();
        if (request.isCargoInsurance()) {
            var cargoPercent = calculateInsuranceCost(4, consigneeCode);
            var cargoInsurance = cargoPercent.multiply(BigDecimal.valueOf(request.getCargoCost())).setScale(2, RoundingMode.HALF_EVEN);
            insuranceCostList.add(new InsuranceResponse(CARGO_INSURANCE_NAME, cargoInsurance, cargoPercent.doubleValue()));
        }
        if (request.isDelayInsurance()) {
            var cargoPercent = calculateInsuranceCost(5, consigneeCode);
            var cargoInsurance = cargoPercent.multiply(BigDecimal.valueOf(request.getCargoCost())).setScale(2, RoundingMode.HALF_EVEN);
            insuranceCostList.add(new InsuranceResponse(DELAY_INSURANCE_NAME, cargoInsurance, cargoPercent.doubleValue()));
        }
        return insuranceCostList;
    }

    private BigDecimal calculateInsuranceCost(int additionalServiceId, String consigneeCode) {
        var additionalServicesPrice = additionalServicesPriceRepository
                .findByAgreement_IdAndAdditionalService_IdAndConsigneeCode(1, additionalServiceId, consigneeCode);
        if (additionalServicesPrice != null && additionalServicesPrice.getPercent() != null) {
            return additionalServicesPrice.getPercent();
        }
        throw new CalculateException(INSURANCE_COST_CALCULATION_ERROR);
    }
}