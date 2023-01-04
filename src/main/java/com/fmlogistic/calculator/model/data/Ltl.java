package com.fmlogistic.calculator.model.data;

import com.fmlogistic.calculator.model.data.internal.Cities;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * LTL для обновления сайта
 */
@Data
@AllArgsConstructor
public class Ltl {
    private Cities cities;
    private List<String> additionalServices;
}
