package ru.omegastroy.portal.mapper;


import ru.omegastroy.portal.model.DeliveryInfo;
import ru.omegastroy.portal.model.PaymentInfo;
import ru.omegastroy.portal.model.dto.DeliveryInfoDto;
import ru.omegastroy.portal.model.dto.PaymentInfoDto;

public interface PaymentInfoMapper {

    PaymentInfoDto toDto(PaymentInfo paymentInfo);

    PaymentInfo fromDto(PaymentInfoDto paymentInfoDto);
}
