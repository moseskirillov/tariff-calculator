package com.fmlogistic.calculator.model.data.internal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Склады для обновления сайта
 */
@Data
@AllArgsConstructor
public class Warehouse {
    private String city;
    private String name;
}
