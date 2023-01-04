package com.fmlogistic.calculator.model.data;

import com.fmlogistic.calculator.model.data.internal.Consignee;
import com.fmlogistic.calculator.model.data.internal.Warehouse;
import com.fmlogistic.calculator.model.data.internal.Zone;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Общий данные для обновления сайта
 */
@Data
@AllArgsConstructor
public class Common {
    private List<Zone> zones;
    private List<Warehouse> warehouses;
    private List<Consignee> consignees;
    private List<String> transportModes;
    private List<String> goodsTypes;
}
