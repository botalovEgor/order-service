package ru.omegastroy.portal.mapper;

import ru.omegastroy.portal.model.BasketItem;
import ru.omegastroy.portal.model.dto.BasketItemDto;

public interface BasketItemMapper {
    BasketItemDto toDto(BasketItem basketItem);

    BasketItem fromDto (BasketItemDto basketItemDto);
}
