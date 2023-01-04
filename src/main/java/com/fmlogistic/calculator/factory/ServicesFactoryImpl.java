package com.fmlogistic.calculator.factory;

import com.fmlogistic.calculator.service.delivery.DeliveryCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicesFactoryImpl implements ServicesFactory {

    private final BeanFactory beanFactory;

    @Override
    public DeliveryCostService getService(String type) throws NoSuchBeanDefinitionException {
        return beanFactory.getBean(type, DeliveryCostService.class);
    }
}