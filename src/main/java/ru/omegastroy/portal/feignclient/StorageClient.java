package ru.omegastroy.portal.feignclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.omegastroy.portal.configs.StorageClientConfiguration;

@ConditionalOnProperty("endpoints.storage_url")
@FeignClient(
        value = "storageClient",
        url = "${endpoints.storage_url}",
        configuration = StorageClientConfiguration.class)
public interface StorageClient {

    @GetMapping("/{id}/amount")
    public Long getAvailableProductAmount(Long catalogItemId);
}
