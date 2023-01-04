package com.fmlogistic.calculator.service.settelment;

import com.fmlogistic.calculator.exception.CalculateException;
import com.fmlogistic.calculator.factory.ServicesFactory;
import com.fmlogistic.calculator.model.request.CostRequest;
import com.fmlogistic.calculator.model.response.CostResponse;
import com.fmlogistic.calculator.model.response.InsuranceResponse;
import com.fmlogistic.calculator.service.additional.AdditionalServicesCostService;
import com.fmlogistic.calculator.service.insurance.InsuranceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private static final String FTL_TARIFF_TYPE = "FTL";
    private static final String LTL_TARIFF_TYPE = "LTL";
    private static final String BOX_TARIFF_TYPE = "BOX";

    private static final String FTL_UNIT_TYPE = "ftl";
    private static final String LTL_UNIT_TYPE = "паллета";
    private static final String BOX_UNIT_TYPE = "килограмм";

    private static final String TARIFF_TYPE_NOT_FOUND_ERROR = "Не верно указан тип тарифа";
    private static final String REQUEST_RECEIVED_LOG = "Получен запрос на расчет стомости доставки: {}";
    private static final String REQUEST_SEND_TO_SERVICE_LOG = "Запрос перенаправлен на сервис: {}";
    private static final String RECEIVED_DELIVERY_COST_LOG = "Получена базовая стоимость доставки: {}";
    private static final String RECEIVED_ADDITIONAL_SERVICES_COST_LOG = "Получена стоимость дополнительных сервисов: {}";
    private static final String RECEIVED_INSURANCE_COST_LOG = "Получена стоиость страховок: {}";
    private static final String DELIVERY_COST_FINAL_LOG = "Обработка запроса завершена, общая стоимость: {}";

    private static final String CARGO_INSURANCE_NAME = "Страхование груза";
    private static final String DELAY_INSURANCE_NAME = "Страхование сроков";

    private static final Map<String, String> UNIT_TYPES_MAP = Map.of(
            FTL_TARIFF_TYPE, FTL_UNIT_TYPE,
            LTL_TARIFF_TYPE, LTL_UNIT_TYPE,
            BOX_TARIFF_TYPE, BOX_UNIT_TYPE
    );

    private final ServicesFactory servicesFactory;
    private final InsuranceService insuranceService;
    private final AdditionalServicesCostService additionalServicesCostService;

    /**
     * Сервис для расчета общей цены, стоимости доставки, страховок и дополнительных услуг
     * @param request запрос
     * @return общая стоиомость
     */
    @Override
    public CostResponse calculate(CostRequest request) {
        log.info(REQUEST_RECEIVED_LOG, request.toString());
        var type = request.getTariffType();
        if (StringUtils.isBlank(type)) {
            throw new CalculateException(TARIFF_TYPE_NOT_FOUND_ERROR);
        }
        var calculationService = servicesFactory.getService(type);
        log.info(REQUEST_SEND_TO_SERVICE_LOG, type);
        var totalCost = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
        var deliveryCost = calculationService.calculate(request);
        if (deliveryCost != null) {
            deliveryCost = deliveryCost.setScale(2, RoundingMode.HALF_EVEN);
            totalCost = totalCost.add(deliveryCost);
        }
        log.info(RECEIVED_DELIVERY_COST_LOG, deliveryCost);
        var unitType = UNIT_TYPES_MAP.get(type);
        var addServices = additionalServicesCostService.calculateAdditionalServicesCost(request, unitType);
        log.info(RECEIVED_ADDITIONAL_SERVICES_COST_LOG, addServices);
        var costInsurance = insuranceService.calculateInsuranceCost(request);
        log.info(RECEIVED_INSURANCE_COST_LOG, costInsurance);
        addServices.putAll(costInsurance
                .stream()
                .collect(Collectors.toMap(
                        InsuranceResponse::getName,
                        InsuranceResponse::getCost))
        );
        for (var service : addServices.entrySet()) {
            totalCost = totalCost.add(service.getValue());
        }
        var cargoInsurance = costInsurance
                .stream()
                .filter(e -> CARGO_INSURANCE_NAME.equals(e.getName()))
                .map(InsuranceResponse::getPercent)
                .findFirst()
                .orElse(null);
        var delayInsurance = costInsurance
                .stream()
                .filter(e -> DELAY_INSURANCE_NAME.equals(e.getName()))
                .map(InsuranceResponse::getPercent)
                .findFirst()
                .orElse(null);
        log.info(DELIVERY_COST_FINAL_LOG, totalCost);
        return new CostResponse(
                totalCost,
                deliveryCost,
                cargoInsurance,
                delayInsurance,
                addServices
        );
    }
}
