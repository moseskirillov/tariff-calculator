package com.fmlogistic.calculator.repository;

import com.fmlogistic.calculator.entity.AdditionalServicesPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий работы с таблицей стомости дополнительных услуг
 */
@Repository
public interface AdditionalServicesPriceRepository extends JpaRepository<AdditionalServicesPrice, Integer> {
    AdditionalServicesPrice findByAgreement_IdAndAdditionalService_IdAndGoodsType_Name(int agreementId, int additionalServiceId, String goodsType);
    AdditionalServicesPrice findByAgreement_IdAndAdditionalService_NameAndUnitType(int agreementId, String serviceName, String unitType);
    AdditionalServicesPrice findByAgreement_IdAndAdditionalService_IdAndConsigneeCode(int agreementId, int additionalServiceId, String consigneeCode);

    @Query("""
                SELECT DISTINCT asp.consigneeCode
                FROM AdditionalServicesPrice asp
                WHERE asp.agreement.id = 1
                AND asp.consigneeCode IS NOT NULL
                AND asp.consigneeCode = :consigneeCode
                AND asp.additionalService.id IN (4, 5)
            """)
    String findConsigneeCode(String consigneeCode);
}
