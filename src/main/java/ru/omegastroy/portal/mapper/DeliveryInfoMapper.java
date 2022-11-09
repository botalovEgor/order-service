package ru.omegastroy.portal.mapper;


import ru.omegastroy.portal.model.DeliveryInfo;
import ru.omegastroy.portal.model.dto.DeliveryInfoDto;

public interface DeliveryInfoMapper {

    DeliveryInfoDto toDto(DeliveryInfo deliveryInfo);

    DeliveryInfo fromDto(DeliveryInfoDto deliveryInfoDto);
}
