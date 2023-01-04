package com.fmlogistic.calculator.service.delivery;

import com.fmlogistic.calculator.exception.CalculateException;
import com.fmlogistic.calculator.model.request.CostRequest;
import com.fmlogistic.calculator.repository.DeliveryPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service("FTL")
@RequiredArgsConstructor
public class DeliveryCostServiceFTL implements DeliveryCostService {

    private static final String DELIVERY_PRICE_CALCULATION_ERROR =
            "Ошибка при расчете стоимости доставки. Пожалуйста, проверьте параметры запроса";

    private final DeliveryPriceRepository deliveryPriceRepository;

    /**
     * Расчет стоимости для FTL
     * @param request запрос
     * @return стоимость доставки
     */
    @Override
    public BigDecimal calculate(CostRequest request) {
        var deliveryPrice = Optional.ofNullable(deliveryPriceRepository.calculateFTL(
                1,
                1,
                request.getSenderCity(),
                request.getSenderCity().equals(request.getConsigneeCity()) ?
                        request.getSenderZone() :
                        null,
                false,
                false,
                request.getConsigneeCity(),
                request.getSenderCity().equals(request.getConsigneeCity()) ?
                        request.getConsigneeZone() :
                        null,
                false,
                false,
                request.getVehicleType(),
                request.getTransportMode()));
        return deliveryPrice.orElseThrow(() -> {
            throw new CalculateException(DELIVERY_PRICE_CALCULATION_ERROR);
        });
    }
}