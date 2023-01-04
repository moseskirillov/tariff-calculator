package com.fmlogistic.calculator.handler;

import com.fmlogistic.calculator.exception.CalculateException;
import com.fmlogistic.calculator.exception.TypeErrorException;
import com.fmlogistic.calculator.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Перехватчики исключений
 */
@Slf4j
@RestControllerAdvice
public class CalculationExceptionHandler {

    /**
     * Исключение ошибочного типа тарифа
     * @param e exception
     * @return error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeErrorException.class)
    public ErrorResponse typeErrorException(TypeErrorException e) {
        log.error("Tariff type error: ", e);
        return new ErrorResponse("Неверно указан тип тарифа: " + e.getMessage(), Map.of());
    }

    /**
     * Исключение ошибочного типа тарифа
     * @param e exception
     * @return error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchBeanDefinitionException.class)
    public ErrorResponse noSuchBeanDefinitionException(NoSuchBeanDefinitionException e) {
        log.error("Tariff type error: ", e);
        return new ErrorResponse(e.getMessage(), Map.of());
    }

    /**
     * Исключение ошибки расчетов
     * @param e exception
     * @return error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CalculateException.class)
    public ErrorResponse calculateException(CalculateException e) {
        log.error("Calculation error: ", e);
        return new ErrorResponse(e.getMessage(), Map.of());
    }

    /**
     * Исключение ошибки валидации
     * @param e exception
     * @return error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse validationException(MethodArgumentNotValidException e) {
        log.error("Valid error: ", e);
        var errors = new HashMap<String, String>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ErrorResponse("Ошибка валидации полей", errors);
    }

    /**
     * Исключение фатальной ошибки
     * @param e exception
     * @return error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse exception(Exception e) {
        log.error("Fatal error: ", e);
        return new ErrorResponse("Fatal error", Map.of());
    }
}