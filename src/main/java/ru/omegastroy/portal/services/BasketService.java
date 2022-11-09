package ru.omegastroy.portal.services;

import ru.omegastroy.portal.model.dto.BasketDto;
import ru.omegastroy.portal.model.dto.BasketItemDto;

import java.util.UUID;

public interface BasketService {
    BasketDto findByClientUuid(UUID clientUuid);

    BasketDto addBasketItem(UUID clientUUid, BasketItemDto basketItemDto);

    BasketDto changeAmountBasketItem(UUID clientUUid, Long basketItemId, Integer amount);

    BasketDto deleteItemFromBasket(UUID clientUUid, Long basketItemId);

    void cleanBasket(UUID clientUUid, Long basketId);
}
