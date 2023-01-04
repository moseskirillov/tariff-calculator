package com.fmlogistic.calculator.controller;

import com.fmlogistic.calculator.model.response.DataResponse;
import com.fmlogistic.calculator.service.data.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер получения данных для фронта сайта
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    @GetMapping
    public ResponseEntity<DataResponse> getData() {
        return ResponseEntity.ok(dataService.getData());
    }
}

