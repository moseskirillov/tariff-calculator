package com.fmlogistic.calculator.exception;

/**
 * Исключение любой ошибки в работе сервисов калькуляции
 */
public class CalculateException extends RuntimeException {
    public CalculateException(String message) {
        super(message);
    }
}
