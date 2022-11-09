package ru.omegastroy.portal.mapper;

import ru.omegastroy.portal.model.Basket;
import ru.omegastroy.portal.model.dto.BasketDto;

public interface BasketMapper {
    BasketDto toDto(Basket basket);

    Basket fromDto (BasketDto basketDto);
}
