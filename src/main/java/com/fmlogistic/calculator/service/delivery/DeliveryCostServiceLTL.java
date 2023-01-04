package com.fmlogistic.calculator.service.delivery;

import com.fmlogistic.calculator.exception.CalculateException;
import com.fmlogistic.calculator.model.request.CostRequest;
import com.fmlogistic.calculator.repository.DeliveryPriceRepository;
import com.fmlogistic.calculator.service.markup.MarkupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service("LTL")
@RequiredArgsConstructor
public class DeliveryCostServiceLTL implements DeliveryCostService {

    private static final String LTL_UNIT_TYPE = "паллета";
    private static final String CONSIGNEE_RETAIL = "Другой грузополучатель";
    private static final String DELIVERY_PRICE_CALCULATION_ERROR =
            "Ошибка при расчете стоимости доставки. Пожалуйста, проверьте параметры запроса";

    private final MarkupService markupService;
    private final DeliveryPriceRepository deliveryPriceRepository;

    /**
     * Расчет стоимости для LTL
     * @param request запрос
     * @return стоимость доставки
     */
    @Override
    public BigDecimal calculate(CostRequest request) {
        var basePrice = new BigDecimal(0);
        var priceToClient = Optional.ofNullable(
                deliveryPriceRepository.calculateLTLForSenderFM(
                        BigDecimal.valueOf(request.getUnitQty()),
                        1,
                        2,
                        request.getSenderCity(),
                        true,
                        false,
                        request.getConsigneeCity(),
                        request.getConsigneeZone(),
                        false,
                        consigneeRetail(request.getConsigneeCode()),
                        request.getTransportMode(),
                        LTL_UNIT_TYPE
                )).orElseThrow(() -> {
            throw new CalculateException(DELIVERY_PRICE_CALCULATION_ERROR);
        });
        if (!request.isSenderFm()) {
            var priceToFM = Optional.ofNullable(
                    deliveryPriceRepository.calculateLTLForSenderClient(
                            BigDecimal.valueOf(request.getUnitQty()),
                            1,
                            2,
                            request.getSenderCity(),
                            request.getSenderZone(),
                            false,
                            false,
                            request.getSenderCity(),
                            request.getConsigneeZone(),
                            true,
                            false,
                            null,
                            LTL_UNIT_TYPE
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

    private boolean consigneeRetail(String consigneeCode) {
        return (StringUtils.isNoneBlank(consigneeCode) && consigneeCode != null)
                && !consigneeCode.equals(CONSIGNEE_RETAIL);
    }
}