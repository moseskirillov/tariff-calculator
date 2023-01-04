package com.fmlogistic.calculator.service.additional;

import com.fmlogistic.calculator.exception.CalculateException;
import com.fmlogistic.calculator.model.request.CostRequest;
import com.fmlogistic.calculator.repository.AdditionalServicesPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdditionalServicesCostServiceImpl implements AdditionalServicesCostService {

    private static final String FTL_TARIFF_TYPE = "FTL";
    private static final int FTL_UNIT_QTY = 1;

    private static final String ADDITIONAL_SERVICE_PRICE_CALCULATION_ERROR =
            "Ошибка при расчете стоимости дополнительных услуг. Пожалуйста, проверьте параметры запроса";

    private final AdditionalServicesPriceRepository additionalServicesPriceRepository;

    /**
     * Расчет стомости доп. услуг
     * @param request запрос
     * @param unitType фтл, паллета, кг
     * @return Map название услуги/стоимость
     */
    @Override
    public Map<String, BigDecimal> calculateAdditionalServicesCost(CostRequest request, String unitType) {
        var unitQty = request.getTariffType().equals(FTL_TARIFF_TYPE) ? FTL_UNIT_QTY : request.getUnitQty();
        var addServices = new HashMap<String, BigDecimal>();
        var services = request.getAdditionalServices();
        if (services == null || services.isEmpty()) {
            return addServices;
        }
        for (var service : request.getAdditionalServices()) {
            var servicesPrice = additionalServicesPriceRepository
                    .findByAgreement_IdAndAdditionalService_NameAndUnitType(
                            1,
                            service.getName(),
                            unitType
                    );
            if (servicesPrice == null || servicesPrice.getPrice() == null) {
                throw new CalculateException(ADDITIONAL_SERVICE_PRICE_CALCULATION_ERROR);
            }
            var price = servicesPrice.getPrice().multiply(BigDecimal.valueOf(unitQty));
            price = price.setScale(2, RoundingMode.HALF_EVEN);
            addServices.put(service.getName(), price);
        }
        return addServices;
    }
}