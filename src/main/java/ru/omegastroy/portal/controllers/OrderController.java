package ru.omegastroy.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.omegastroy.portal.model.dto.*;
import ru.omegastroy.portal.services.BasketService;
import ru.omegastroy.portal.services.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> findByClientUuid(@RequestParam UUID clientUuid) {
        return orderService.findOrderByClientUuid(clientUuid);
    }

    @PostMapping
    public OrderDto createOder(@RequestBody OrderDto orderDto){
        return orderService.createOder(orderDto);
    }

    @PutMapping("/{orderId}/delivery-info")
    public OrderDto updateDeliveryInfo(@PathVariable Long orderId,
                                 @RequestBody DeliveryInfoDto dto){
        return orderService.updateDeliveryInfo(orderId, dto);

    }

    @PutMapping("/{orderId}/payment-info")
    public OrderDto updatePaymentInfo(@PathVariable Long orderId,
                                @RequestBody PaymentInfoDto dto){
        return orderService.updatePaymentInfo(orderId, dto);

    }

    @PostMapping("/{orderId}/confirm")
    public void confirmOrder(@PathVariable Long orderId){
        orderService.confirmOrder(orderId);
    }
}
