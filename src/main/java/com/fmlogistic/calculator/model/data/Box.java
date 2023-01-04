package com.fmlogistic.calculator.model.data;

import com.fmlogistic.calculator.model.data.internal.Cities;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * BOX для обновления сайта
 */
@Data
@AllArgsConstructor
public class Box {
    private Cities cities;
    private List<String> additionalServices;
}
