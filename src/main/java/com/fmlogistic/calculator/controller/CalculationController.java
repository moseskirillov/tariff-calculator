package com.fmlogistic.calculator.controller;

import com.fmlogistic.calculator.model.request.CostRequest;
import com.fmlogistic.calculator.model.response.CostResponse;
import com.fmlogistic.calculator.service.settelment.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер сервиса расчета стоимости доставки
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate")
public class CalculationController {

    private final SettlementService settlementService;

    /**
     * Главный метод расчета
     * @param request запрос с параметрами для расчета
     * @return результат калькуляции
     */
    @PostMapping
    public ResponseEntity<CostResponse> calculate(@RequestBody CostRequest request) {
        return ResponseEntity.ok(settlementService.calculate(request));
    }
}