package com.fmlogistic.calculator.repository;

import com.fmlogistic.calculator.entity.TransportMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с таблицей видов транспорта по типу
 */
@Repository
public interface TransportModeRepository extends JpaRepository<TransportMode, Integer> {
}
