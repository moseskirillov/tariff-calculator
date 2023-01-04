package com.fmlogistic.calculator.model.data.internal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * FTL города для обновления сайта
 */
@Data
@AllArgsConstructor
public class FTLCities {
    private List<String> consigneeCities;
    private List<String> senderCities;
}
