package ru.omegastroy.portal.configs;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableFeignClients
public class StorageClientConfiguration {
    @Value("${app.auth.basic.storageLogin}")
    private String eipLogin = "";

    @Value("${app.auth.basic.storagePassword}")
    private String eipPassword = "";

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(eipLogin, eipPassword);
    }
}
