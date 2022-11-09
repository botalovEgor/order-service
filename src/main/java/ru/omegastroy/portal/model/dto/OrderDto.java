package ru.omegastroy.portal.model.dto;

import lombok.Data;
import ru.omegastroy.portal.model.PaymentInfo;

import java.util.UUID;

@Data
public class OrderDto {
    private Long id;
    private UUID clientUUID;
    private OrderStatusCatalogDto status;
    private BasketDto basket;
    private PaymentInfo paymentInfoDto;
    private DeliveryInfoDto deliveryInfoDto;
}
