package ru.omegastroy.portal.services;

import ru.omegastroy.portal.model.dto.DeliveryInfoDto;
import ru.omegastroy.portal.model.dto.OrderDto;
import ru.omegastroy.portal.model.dto.PaymentInfoDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderDto> findOrderByClientUuid(UUID clientUuid);

    OrderDto createOder(OrderDto orderDto);

    OrderDto updateDeliveryInfo(Long orderId, DeliveryInfoDto dto);

    OrderDto updatePaymentInfo(Long orderId, PaymentInfoDto dto);

    void confirmOrder(Long orderId);

    void paymentConfirmed(Long orderId);

    void storageConfirmed(Long orderId);

    void deliveryRuned(Long orderId);

    void deliveryCompleted(Long orderId);
}
