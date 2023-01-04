package com.fmlogistic.calculator.repository;

import com.fmlogistic.calculator.entity.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с таблицей типов товара
 */
@Repository
public interface GoodsTypeRepository extends JpaRepository<GoodsType, Integer> {
}
