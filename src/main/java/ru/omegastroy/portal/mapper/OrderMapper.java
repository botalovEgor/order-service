package ru.omegastroy.portal.mapper;


import ru.omegastroy.portal.model.Order;
import ru.omegastroy.portal.model.dto.OrderDto;

public interface OrderMapper {

    OrderDto toDto(Order order);

    Order fromDto(OrderDto orderDto);
}
