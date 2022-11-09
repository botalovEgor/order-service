package ru.omegastroy.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.omegastroy.portal.model.dto.BasketDto;
import ru.omegastroy.portal.model.dto.BasketItemDto;
import ru.omegastroy.portal.services.BasketService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @GetMapping
    public BasketDto findByClientUuid(@RequestParam UUID clientUuid) {
        return basketService.findByClientUuid(clientUuid);
    }

    @PutMapping("/item")
    public BasketDto addBasketItem(@RequestParam UUID clientUUid, @RequestBody BasketItemDto basketItemDto){
        return basketService.addBasketItem(clientUUid, basketItemDto);
    }

    @PutMapping("/item/{basketItemId}/amount")
    public BasketDto changeAmountBasketItem(@RequestParam UUID clientUUid,
                                     @PathVariable Long basketItemId,
                                     @RequestParam Integer amount){
        return basketService.changeAmountBasketItem(clientUUid, basketItemId, amount);

    }
    @DeleteMapping("/item/{basketItemId}")
    public BasketDto deleteItemFromBasket(UUID clientUUid, Long basketItemId){
        return basketService.deleteItemFromBasket(clientUUid, basketItemId);
    }

    @PostMapping("/clean")
    public void cleanBasket(UUID clientUUid, Long basketId){
        basketService.cleanBasket(clientUUid, basketId);
    }
}
