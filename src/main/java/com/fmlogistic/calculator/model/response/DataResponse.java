package com.fmlogistic.calculator.model.response;

import com.fmlogistic.calculator.model.data.Box;
import com.fmlogistic.calculator.model.data.Common;
import com.fmlogistic.calculator.model.data.Ftl;
import com.fmlogistic.calculator.model.data.Ltl;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO, летящий на фронт как ответ на запрос данных для обновления сайта
 */
@Data
@AllArgsConstructor
public class DataResponse {
    Ftl ftl;
    Ltl ltl;
    Box box;
    Common common;
}
