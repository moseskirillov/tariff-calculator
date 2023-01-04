package com.fmlogistic.calculator.model.data.internal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Грузополучатели для обновления сайта
 */
@Data
@AllArgsConstructor
public class Consignee {
    private String city;
    private String name;
}
