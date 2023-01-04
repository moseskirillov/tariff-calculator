package com.fmlogistic.calculator.model.data.internal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Города для обновления сайта
 */
@Data
@AllArgsConstructor
public class Cities {
    private List<String> consigneeCities;
    private List<String> senderCities;
    private List<String> senderCitiesFM;
}
