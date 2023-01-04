package com.fmlogistic.calculator.model.data;

import com.fmlogistic.calculator.model.data.internal.FTLCities;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * FTL для обновления сайта
 */
@Data
@AllArgsConstructor
public class Ftl {
    private FTLCities cities;
    private List<String> vehicleTypes;
    private List<String> additionalServices;
}
