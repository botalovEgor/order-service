package ru.omegastroy.portal.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.omegastroy.portal.model.Basket;

import java.util.List;

@Data
@NoArgsConstructor
public class BasketDto {
    private Long id;
    private OrderDto order;
    private List<BasketItemDto> items;

}
