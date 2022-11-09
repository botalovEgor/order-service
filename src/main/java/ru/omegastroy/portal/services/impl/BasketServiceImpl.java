package ru.omegastroy.portal.services.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import ru.omegastroy.portal.feignclient.StorageClient;
import ru.omegastroy.portal.mapper.BasketItemMapper;
import ru.omegastroy.portal.mapper.BasketMapper;
import ru.omegastroy.portal.model.Basket;
import ru.omegastroy.portal.model.dto.BasketDto;
import ru.omegastroy.portal.model.dto.BasketItemDto;
import ru.omegastroy.portal.repository.BasketRepository;
import ru.omegastroy.portal.services.BasketService;
import ru.omegastroy.portal.utils.AuthUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final StorageClient storageClient;
    private final BasketMapper basketMapper;
    private final BasketItemMapper basketItemMapper;
    private final AuthUtils authUtils;


    @Override
    public BasketDto findByClientUuid(UUID clientUuid) {
        return basketMapper.toDto(getBasket(clientUuid));
    }

    @Override
    @Transactional
    public BasketDto addBasketItem(UUID clientUUid, BasketItemDto basketItemDto) {
        Basket basket = getBasket(clientUUid);

        Long availableProductAmount = storageClient.getAvailableProductAmount(basketItemDto.getCatalogItemId());

        boolean itemIsPresent = basket.getItems()
                .stream()
                .filter(it -> it.getCatalogItemId().equals(basketItemDto.getCatalogItemId()))
                .peek(it -> {
                    if ((it.getAmount() + basketItemDto.getAmount()) > availableProductAmount) {
                        throw new ServiceException("Указанное количество товара недоступно для заказа");
                    } else {
                        it.setAmount(it.getAmount() + basketItemDto.getAmount());
                    }
                })
                .findAny()
                .isPresent();

        if (!itemIsPresent && basketItemDto.getAmount() <= availableProductAmount) {
            basket.getItems().add(basketItemMapper.fromDto(basketItemDto));
        } else {
            throw new ServiceException("Указанное количество товара недоступно для заказа");
        }

        Basket saved = basketRepository.save(basket);

        return basketMapper.toDto(saved);
    }

    @Override
    public BasketDto changeAmountBasketItem(UUID clientUUid, Long basketItemId, Integer amount) {
        Long availableProductAmount = storageClient.getAvailableProductAmount(basketItemId);
        Basket basket = getBasket(clientUUid);
        basket.getItems()
                .stream()
                .filter(it -> it.getId().equals(basketItemId))
                .forEach(it -> {
                    if (it.getAmount() + amount > availableProductAmount) {
                        throw new ServiceException("Указанное количество товара недоступно для заказа");
                    } else {
                        it.setAmount(it.getAmount() + amount);
                    }

                });

        Basket saved = basketRepository.save(basket);

        return basketMapper.toDto(saved);
    }

    @Override
    public BasketDto deleteItemFromBasket(UUID clientUUid, Long basketItemId) {

        Basket basket = getBasket(clientUUid);
        basket.getItems()
                .stream()
                .filter(it -> it.getId().equals(basketItemId))
                .forEach(it -> basket.getItems().remove(it));

        Basket saved = basketRepository.save(basket);

        return basketMapper.toDto(saved);
    }

    @Override
    public void cleanBasket(UUID clientUUid, Long basketId) {
        Basket basket = getBasket(clientUUid);
        basket.setItems(Collections.emptyList());
        basketRepository.save(basket);
    }

    private Basket getBasket(UUID clientUuid) {
        Optional<Basket> basketOptional = basketRepository.findByClientUuid(clientUuid);
        if(basketOptional.isPresent()) {
            return basketOptional.get();
        } else {
            Basket basket = new Basket();
            basket.setClientUuid(clientUuid == null?authUtils.getCurrentUserUuid():clientUuid);
            return basketRepository.save(basket);
        }
    }
}
