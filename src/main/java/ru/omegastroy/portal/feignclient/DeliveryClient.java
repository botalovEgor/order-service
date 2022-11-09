package ru.omegastroy.portal.feignclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.omegastroy.portal.configs.DeliveryClientConfiguration;

@ConditionalOnProperty("endpoints.delivery_url")
@FeignClient(
        value = "storageClient",
        url = "${endpoints.storage_url}",
        configuration = DeliveryClientConfiguration.class)
public interface DeliveryClient {

    @GetMapping("/{id}/amount")
    public Long getAvailableProductAmount(Long catalogItemId);
}
