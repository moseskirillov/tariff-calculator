package com.fmlogistic.calculator.repository;

import com.fmlogistic.calculator.entity.ConsigneeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с таблицей грузополучателей
 */
@Repository
public interface ConsigneeListRepository extends JpaRepository<ConsigneeList, Integer> {
}
