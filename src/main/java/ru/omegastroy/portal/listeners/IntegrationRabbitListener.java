package ru.omegastroy.portal.listeners;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.omegastroy.portal.services.OrderService;

@Component
@RequiredArgsConstructor
public class IntegrationRabbitListener {
    private final OrderService orderService;


    @RabbitListener(queuesToDeclare = @Queue("${rabbitmq.queues.payment_confirmed}"))
    private void paymentConfirmed(Long orderId){
        orderService.paymentConfirmed(orderId);
    }


    @RabbitListener(queuesToDeclare = @Queue("${rabbitmq.queues.storage_confirmed}"))
    private void storageConfirmed(Long orderId){
        orderService.storageConfirmed(orderId);
    }

    @RabbitListener(queuesToDeclare = @Queue("${rabbitmq.queues.delivery_runed}"))
    private void deliveryRuned(Long orderId){
        orderService.deliveryRuned(orderId);
    }

    @RabbitListener(queuesToDeclare = @Queue("${rabbitmq.queues.delivery_completed}"))
    private void deliveryCompleted(Long orderId){
        orderService.deliveryCompleted(orderId);
    }
}
