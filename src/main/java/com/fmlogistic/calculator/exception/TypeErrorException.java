package com.fmlogistic.calculator.exception;

/**
 * Исключение для невалидного типа тарифа
 */
public class TypeErrorException extends RuntimeException {
    public TypeErrorException(String message) {
        super(message);
    }
}
