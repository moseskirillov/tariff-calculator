package com.fmlogistic.calculator.service.data;

import com.fmlogistic.calculator.entity.AdditionalService;
import com.fmlogistic.calculator.entity.AdditionalServicesPrice;
import com.fmlogistic.calculator.entity.DeliveryPrice;
import com.fmlogistic.calculator.entity.GoodsType;
import com.fmlogistic.calculator.entity.TransportMode;
import com.fmlogistic.calculator.entity.VehicleType;
import com.fmlogistic.calculator.model.data.Box;
import com.fmlogistic.calculator.model.data.Common;
import com.fmlogistic.calculator.model.data.Ftl;
import com.fmlogistic.calculator.model.data.Ltl;
import com.fmlogistic.calculator.model.data.internal.Cities;
import com.fmlogistic.calculator.model.data.internal.Consignee;
import com.fmlogistic.calculator.model.data.internal.FTLCities;
import com.fmlogistic.calculator.model.data.internal.Warehouse;
import com.fmlogistic.calculator.model.data.internal.Zone;
import com.fmlogistic.calculator.model.response.DataResponse;
import com.fmlogistic.calculator.repository.AdditionalServicesPriceRepository;
import com.fmlogistic.calculator.repository.ConsigneeListRepository;
import com.fmlogistic.calculator.repository.DeliveryPriceRepository;
import com.fmlogistic.calculator.repository.GoodsTypeRepository;
import com.fmlogistic.calculator.repository.TransportModeRepository;
import com.fmlogistic.calculator.repository.VehicleTypeRepository;
import com.fmlogistic.calculator.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private static final String FTL_UNIT_TYPE = "ftl";
    private static final String PALLET_UNIT_TYPE = "паллета";
    private static final String KILOGRAM_UNIT_TYPE = "килограмм";

    private final GoodsTypeRepository goodsTypeRepository;
    private final WarehouseRepository warehouseRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final TransportModeRepository transportModeRepository;
    private final DeliveryPriceRepository deliveryPriceRepository;
    private final ConsigneeListRepository consigneeListRepository;
    private final AdditionalServicesPriceRepository additionalServicesPriceRepository;

    @Override
    public DataResponse getData() {
        var allCities = deliveryPriceRepository.findAll();
        var additionalServices = additionalServicesPriceRepository.findAll();
        return new DataResponse(
                getDataFtl(allCities, additionalServices),
                getDataLtl(allCities, additionalServices),
                getDataBox(allCities, additionalServices),
                getDataCommon(allCities)
        );
    }

    /**
     * Данные FTL
     * @param allCities список городов
     * @param additionalServices доп. услуги
     * @return модель данных для FTL
     */
    private Ftl getDataFtl(List<DeliveryPrice> allCities, List<AdditionalServicesPrice> additionalServices) {
        var cities = allCities
                .stream()
                .filter(e -> e.getUnitType()
                        .equals(FTL_UNIT_TYPE) && !e.isConsigneeFM())
                .toList();
        return new Ftl(
                new FTLCities(
                        cities.stream()
                                .map(DeliveryPrice::getConsigneeCity)
                                .distinct()
                                .sorted()
                                .toList(),
                        cities.stream()
                                .map(DeliveryPrice::getSenderCity)
                                .distinct()
                                .sorted()
                                .toList()),
                vehicleTypeRepository.findAll()
                        .stream()
                        .map(VehicleType::getName)
                        .distinct()
                        .sorted()
                        .toList(),
                getAdditionalServices(additionalServices, FTL_UNIT_TYPE)
        );
    }

    /**
     * Данные LTL
     * @param allCities список городов
     * @param additionalServices доп. услуги
     * @return модель данных для LTL
     */
    private Ltl getDataLtl(List<DeliveryPrice> allCities, List<AdditionalServicesPrice> additionalServices) {
        return new Ltl(
                getCities(allCities
                        .stream()
                        .filter(e -> e.getUnitType().equals(PALLET_UNIT_TYPE))
                        .toList()),
                getAdditionalServices(additionalServices, PALLET_UNIT_TYPE));
    }

    /**
     * Данные BOX
     * @param allCities список городов
     * @param additionalServices доп. услуги
     * @return модель данных для BOX
     */
    private Box getDataBox(List<DeliveryPrice> allCities, List<AdditionalServicesPrice> additionalServices) {
        return new Box(
                getCities(allCities
                        .stream()
                        .filter(e -> e.getUnitType().equals(KILOGRAM_UNIT_TYPE))
                        .toList()),
                getAdditionalServices(additionalServices, KILOGRAM_UNIT_TYPE)
        );
    }

    /**
     * Общие данные для сайта (зоны, склады, получатели и т. д.)
     * @param allCities список городов
     * @return модель общих данных
     */
    private Common getDataCommon(List<DeliveryPrice> allCities) {
        return new Common(
                allCities.stream()
                        .filter(e -> e.getConsigneeZone() != null)
                        .sorted(Comparator.comparing(DeliveryPrice::getConsigneeCity))
                        .map(e -> new Zone(e.getConsigneeCity(), e.getConsigneeZone()))
                        .distinct()
                        .toList(),
                warehouseRepository.findAll().stream()
                        .sorted(Comparator.comparing(com.fmlogistic.calculator.entity.Warehouse::getCity))
                        .map(e -> new Warehouse(e.getCity(), e.getAddress()))
                        .distinct()
                        .toList(),
                consigneeListRepository.findAll()
                        .stream()
                        .map(e -> new Consignee(e.getName(), e.getCity()))
                        .distinct()
                        .toList(),
                transportModeRepository.findAll()
                        .stream()
                        .map(TransportMode::getName)
                        .distinct()
                        .sorted()
                        .toList(),
                goodsTypeRepository.findAll()
                        .stream()
                        .filter(e -> e.getId() != 1)
                        .map(GoodsType::getName)
                        .distinct()
                        .sorted()
                        .toList()
        );
    }

    /**
     * Получение списка уникальных городов
     * @param cities все городп
     * @return список городов
     */
    private Cities getCities(List<DeliveryPrice> cities) {
        return new Cities(
                cities.stream()
                        .filter(e -> !e.isConsigneeFM())
                        .map(DeliveryPrice::getConsigneeCity)
                        .distinct()
                        .sorted()
                        .toList(),
                cities.stream()
                        .filter(e -> !e.isSenderFm())
                        .map(DeliveryPrice::getSenderCity)
                        .distinct()
                        .sorted()
                        .toList(),
                cities.stream()
                        .filter(DeliveryPrice::isSenderFm)
                        .map(DeliveryPrice::getSenderCity)
                        .distinct()
                        .sorted()
                        .toList());
    }

    /**
     * Получение доп. услуг
     * @param additionalServices все доп. услуги из базы
     * @param unitType тип юнита
     * @return список доп. услуг
     */
    private List<String> getAdditionalServices(List<AdditionalServicesPrice> additionalServices, String unitType) {
        return additionalServices
                .stream()
                .filter(e -> e.getUnitType() != null && e.getUnitType().equals(unitType))
                .map(AdditionalServicesPrice::getAdditionalService)
                .map(AdditionalService::getName)
                .distinct()
                .sorted()
                .toList();
    }
}
