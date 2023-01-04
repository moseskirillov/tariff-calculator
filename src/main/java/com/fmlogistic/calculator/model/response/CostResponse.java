package com.fmlogistic.calculator.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * DTO, летящий на фронт как ответ на запрос расчета стоимости
 */
@Data
@AllArgsConstructor
public class CostResponse {
    private BigDecimal totalCost;
    private BigDecimal deliveryCost;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double cargoPercent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double delayPercent;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, BigDecimal> addServices;
}
