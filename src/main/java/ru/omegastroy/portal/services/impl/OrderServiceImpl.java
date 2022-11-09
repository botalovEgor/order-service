package ru.omegastroy.portal.services.impl;

import liquibase.exception.ServiceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.omegastroy.portal.mapper.BasketMapper;
import ru.omegastroy.portal.mapper.DeliveryInfoMapper;
import ru.omegastroy.portal.mapper.OrderMapper;
import ru.omegastroy.portal.mapper.PaymentInfoMapper;
import ru.omegastroy.portal.model.DeliveryInfo;
import ru.omegastroy.portal.model.Order;
import ru.omegastroy.portal.model.PaymentInfo;
import ru.omegastroy.portal.model.dto.DeliveryInfoDto;
import ru.omegastroy.portal.model.dto.OrderDto;
import ru.omegastroy.portal.model.dto.PaymentInfoDto;
import ru.omegastroy.portal.repository.OrderRepository;
import ru.omegastroy.portal.repository.OrderStatusRepository;
import ru.omegastroy.portal.services.OrderService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderMapper orderMapper;
    private final DeliveryInfoMapper deliveryInfoMapper;
    private final PaymentInfoMapper paymentInfoMapper;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.queue.storage_info}")
    private String storageQueue;

    @Value("${rabbit.queue.delivery_info}")
    private String deliveryQueue;


    @Override
    public List<OrderDto> findOrderByClientUuid(UUID clientUuid) {
        return orderRepository.findByClientUUID(clientUuid)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto createOder(OrderDto orderDto) {
        Order saved = orderRepository.save(orderMapper.fromDto(orderDto));
        return orderMapper.toDto(saved);
    }

    @Override
    public OrderDto updateDeliveryInfo(Long orderId, DeliveryInfoDto dto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ServiceNotFoundException(String.format("Заказ с id %d не найден", orderId)));

        DeliveryInfo deliveryInfo = deliveryInfoMapper.fromDto(dto);
        
        order.setDeliveryInfo(deliveryInfo);

        Order saved = orderRepository.save(order);
        
        return orderMapper.toDto(saved);
    }

    @Override
    public OrderDto updatePaymentInfo(Long orderId, PaymentInfoDto dto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ServiceNotFoundException(String.format("Заказ с id %d не найден", orderId)));

        PaymentInfo paymentInfo = paymentInfoMapper.fromDto(dto);

        order.setPaymentInfo(paymentInfo);

        Order saved = orderRepository.save(order);

        return orderMapper.toDto(saved);
    }

    @Override
    public void confirmOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ServiceNotFoundException(String.format("Заказ с id %d не найден", orderId)));

        orderRepository.save(order);

        rabbitTemplate.convertAndSend(storageQueue, orderMapper.toDto(order));

        order.setStatus(orderStatusRepository.findByCode("STORAGE_PACKAGE")
                .orElseThrow(() -> new ServiceNotFoundException("Статус не найден")));

        rabbitTemplate.convertAndSend(deliveryQueue, deliveryInfoMapper.toDto(order.getDeliveryInfo()));
    }

    @Override
    public void paymentConfirmed(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ServiceNotFoundException(String.format("Заказ с id %d не найден", orderId)));

        order.getPaymentInfo().setPaymentStatus("оплачено");
        orderRepository.save(order);
    }


    @Override
    public void storageConfirmed(Long orderId) {
        changeOrderStatus(orderId, "READY_TO_DELIVERY");
    }


    @Override
    public void deliveryRuned(Long orderId) {
        changeOrderStatus(orderId, "DELIVERY_RUNED");
    }

    @Override
    public void deliveryCompleted(Long orderId) {
        changeOrderStatus(orderId, "DELIVERY_COMPLETED");
        paymentConfirmed(orderId);
    }

    private void changeOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ServiceNotFoundException(String.format("Заказ с id %d не найден", orderId)));

        order.setStatus(orderStatusRepository.findByCode(status)
                .orElseThrow(() -> new ServiceNotFoundException("Статус не найден")));
        orderRepository.save(order);
    }
}
