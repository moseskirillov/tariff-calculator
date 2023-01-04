package com.fmlogistic.calculator.service.delivery;

import com.fmlogistic.calculator.exception.CalculateException;
import com.fmlogistic.calculator.model.request.CostRequest;
import com.fmlogistic.calculator.repository.DeliveryPriceRepository;
import com.fmlogistic.calculator.service.markup.MarkupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service("BOX")
@RequiredArgsConstructor
public class DeliveryCostServiceBOX implements DeliveryCostService {

    private static final String BOX_UNIT_TYPE = "килограмм";
    private static final String DELIVERY_PRICE_CALCULATION_ERROR =
            "Ошибка при расчете стоимости доставки. Пожалуйста, проверьте параметры запроса";

    private final MarkupService markupService;
    private final DeliveryPriceRepository deliveryPriceRepository;

    /**
     * Расчет стоимости для BOX
     * @param request запрос
     * @return стоимость доставки
     */
    @Override
    public BigDecimal calculate(CostRequest request) {
        var basePrice = new BigDecimal(0);
        var priceToClient = Optional.ofNullable(
                deliveryPriceRepository.calculateBOXForSenderFM(
                        BigDecimal.valueOf(request.getUnitQty()),
                        1,
                        2,
                        request.getSenderCity(),
                        request.getConsigneeZone(),
                        true,
                        false,
                        request.getConsigneeCity(),
                        false,
                        false,
                        request.getTransportMode(),
                        BOX_UNIT_TYPE
                )).orElseThrow(() -> {
            throw new CalculateException(DELIVERY_PRICE_CALCULATION_ERROR);
        });
        if (!request.isSenderFm()) {
            var priceToFM = Optional.ofNullable(
                    deliveryPriceRepository.calculateBOXForSenderClient(
                            BigDecimal.valueOf(request.getUnitQty()),
                            1,
                            2,
                            request.getSenderCity(),
                            request.getSenderZone(),
                            false,
                            false,
                            request.getSenderCity(),
                            true,
                            false,
                            null,
                            BOX_UNIT_TYPE
                    )).orElseThrow(() -> {
                throw new CalculateException(DELIVERY_PRICE_CALCULATION_ERROR);
            });
            basePrice = basePrice.add(priceToFM);
        }
        basePrice = basePrice.add(priceToClient);
        var multiplier = markupService.calculatePercent(request.getGoodsType());
        log.info("Получен процент наценки: {}", multiplier);
        if (multiplier != null) {
            basePrice = basePrice.multiply(multiplier);
        }
        return basePrice;
    }
}