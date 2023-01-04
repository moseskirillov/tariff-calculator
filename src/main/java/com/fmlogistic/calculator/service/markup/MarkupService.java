package com.fmlogistic.calculator.service.markup;

import java.math.BigDecimal;

/**
 * Сервис расчета наценки за тип товара
 */
public interface MarkupService {
    BigDecimal calculatePercent(String goodsType);
}
