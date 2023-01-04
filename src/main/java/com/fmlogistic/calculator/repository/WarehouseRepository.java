package com.fmlogistic.calculator.repository;

import com.fmlogistic.calculator.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с таблицей списка складов
 */
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
