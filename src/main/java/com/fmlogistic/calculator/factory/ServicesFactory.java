package com.fmlogistic.calculator.factory;

import com.fmlogistic.calculator.service.delivery.DeliveryCostService;

/**
 * Фабрика получения имплементации сервиса по типу
 */
public interface ServicesFactory {
    DeliveryCostService getService(String type);
}