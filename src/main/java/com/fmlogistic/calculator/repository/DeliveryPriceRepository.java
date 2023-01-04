package com.fmlogistic.calculator.repository;

import com.fmlogistic.calculator.entity.DeliveryPrice;
import com.fmlogistic.calculator.entity.TransportMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Репозиторий для работы с таблицей стоимости доставки
 */
@Repository
public interface DeliveryPriceRepository extends JpaRepository<DeliveryPrice, Integer> {
    @Query("""
            SELECT p.basePrice
            FROM DeliveryPrice p
            WHERE p.agreement.id = :agreementId
            AND p.calculationMethod.id = :calculationMethodId
            AND p.senderCity = :senderCity
            AND (:senderZone IS NULL OR p.senderZone = :senderZone)
            AND p.senderRetail = :senderRetail
            AND p.senderFm = :senderFM
            AND p.consigneeCity = :consigneeCity
            AND (:consigneeZone IS NULL OR p.consigneeZone = :consigneeZone)
            AND p.consigneeRetail = :consigneeRetail
            AND p.consigneeFM = :consigneeFM
            AND p.vehicleType.name = :vehicleTypeName
            AND p.transportMode.name = :transportModeName
            """)
    BigDecimal calculateFTL(
            int agreementId,
            int calculationMethodId,
            String senderCity,
            String senderZone,
            boolean senderRetail,
            boolean senderFM,
            String consigneeCity,
            String consigneeZone,
            boolean consigneeRetail,
            boolean consigneeFM,
            String vehicleTypeName,
            String transportModeName
    );

    @Query("""
            SELECT p.basePrice * :unitQty
            FROM DeliveryPrice p
            WHERE p.agreement.id = :agreement
            AND p.calculationMethod.id = :calculationMethod
            AND p.senderCity = :senderCity
            AND p.senderFm = :senderFM
            AND p.senderRetail = :senderRetail
            AND p.consigneeCity = :consigneeCity
            AND (:consigneeZone IS NULL OR p.consigneeZone = :consigneeZone)
            AND p.consigneeFM = :consigneeFM
            AND p.consigneeRetail = :consigneeRetail
            AND (:transportMode IS NULL OR p.transportMode.name = :transportMode)
            AND p.unitType = :unitType
            AND :unitQty BETWEEN p.fromQty AND p.toQty
            """)
    BigDecimal calculateLTLForSenderFM(
            BigDecimal unitQty,
            int agreement,
            int calculationMethod,
            String senderCity,
            boolean senderFM,
            boolean senderRetail,
            String consigneeCity,
            String consigneeZone,
            boolean consigneeFM,
            boolean consigneeRetail,
            String transportMode,
            String unitType
    );

    @Query("""
            SELECT p.basePrice * :unitQty
            FROM DeliveryPrice p
            WHERE p.agreement.id = :agreement
            AND p.calculationMethod.id = :calculationMethod
            AND p.senderCity = :senderCity
            AND (:senderZone IS NULL OR p.senderZone = :senderZone)
            AND p.senderFm = :senderFM
            AND p.senderRetail = :senderRetail
            AND p.consigneeCity = :consigneeCity
            AND (:consigneeZone IS NULL OR p.consigneeZone = :consigneeZone)
            AND p.consigneeFM = :consigneeFM
            AND p.consigneeRetail = :consigneeRetail
            AND (:transportMode IS NULL OR p.transportMode = :transportMode)
            AND p.unitType = :unitType
            AND :unitQty BETWEEN p.fromQty AND p.toQty
            """)
    BigDecimal calculateLTLForSenderClient(
            BigDecimal unitQty,
            int agreement,
            int calculationMethod,
            String senderCity,
            String senderZone,
            boolean senderFM,
            boolean senderRetail,
            String consigneeCity,
            String consigneeZone,
            boolean consigneeFM,
            boolean consigneeRetail,
            TransportMode transportMode,
            String unitType
    );

    @Query(value = """
            SELECT CASE WHEN (p.basePrice * :unitQty) < p.minimalPrice
            THEN p.minimalPrice
            ELSE (p.basePrice * :unitQty) END AS price
            FROM DeliveryPrice p
            WHERE p.agreement.id = :agreement
            AND p.calculationMethod.id = :calculationMethod
            AND p.senderCity = :senderCity
            AND (:consigneeZone IS NULL OR p.consigneeZone = :consigneeZone)
            AND p.senderFm = :senderFM
            AND p.senderRetail = :senderRetail
            AND p.consigneeCity = :consigneeCity
            AND p.consigneeFM = :consigneeFM
            AND p.consigneeRetail = :consigneeRetail
            AND p.transportMode.name = :transportModeName
            AND p.unitType = :unitType
            AND :unitQty BETWEEN p.fromQty AND p.toQty
            """
    )
    BigDecimal calculateBOXForSenderFM(
            BigDecimal unitQty,
            int agreement,
            int calculationMethod,
            String senderCity,
            String consigneeZone,
            boolean senderFM,
            boolean senderRetail,
            String consigneeCity,
            boolean consigneeFM,
            boolean consigneeRetail,
            String transportModeName,
            String unitType
    );

    @Query(value = """
            SELECT CASE WHEN (p.basePrice * :unitQty) < p.minimalPrice
            THEN p.minimalPrice
            ELSE (p.basePrice * :unitQty) END AS price
            FROM DeliveryPrice p
            WHERE p.agreement.id = :agreement
            AND p.calculationMethod.id = :calculationMethod
            AND p.senderCity = :senderCity
            AND p.senderZone = :senderZone
            AND p.senderFm = :senderFM
            AND p.senderRetail = :senderRetail
            AND p.consigneeCity = :consigneeCity
            AND p.consigneeFM = :consigneeFM
            AND p.consigneeRetail = :consigneeRetail
            AND (:transportMode IS NULL OR p.transportMode = :transportMode)
            AND p.unitType = :unitType
            AND :unitQty BETWEEN p.fromQty AND p.toQty
            """
    )
    BigDecimal calculateBOXForSenderClient(
            BigDecimal unitQty,
            int agreement,
            int calculationMethod,
            String senderCity,
            String senderZone,
            boolean senderFM,
            boolean senderRetail,
            String consigneeCity,
            boolean consigneeFM,
            boolean consigneeRetail,
            TransportMode transportMode,
            String unitType
    );
}
