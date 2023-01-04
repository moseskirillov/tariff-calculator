package com.fmlogistic.calculator.repository;

import com.fmlogistic.calculator.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с таблицей видов транспорта по весу
 */
@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {
}
