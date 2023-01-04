package com.fmlogistic.calculator.service.markup;

import com.fmlogistic.calculator.repository.AdditionalServicesPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MarkupServiceImpl implements MarkupService {

    private final AdditionalServicesPriceRepository additionalServicesPriceRepository;

    /**
     * Расчет наценки за тип товара
     * @param goodsTypeName наименование типа товара
     * @return процет наценки или null
     */
    @Override
    public BigDecimal calculatePercent(String goodsTypeName) {
        var addServicePrice = additionalServicesPriceRepository.findByAgreement_IdAndAdditionalService_IdAndGoodsType_Name(1, 3, goodsTypeName);
        return addServicePrice != null && addServicePrice.getPercent() != null ? addServicePrice.getPercent() : null;
    }
}