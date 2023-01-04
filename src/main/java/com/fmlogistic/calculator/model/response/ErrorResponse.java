package com.fmlogistic.calculator.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * DTO, летящий на фронт как ответ на ошибочный запрос
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> errorFields;
}
