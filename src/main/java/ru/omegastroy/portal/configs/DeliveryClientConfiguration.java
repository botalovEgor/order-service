package ru.omegastroy.portal.configs;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class DeliveryClientConfiguration {
    @Value("${app.auth.basic.deliveryLogin}")
    private String eipLogin = "";

    @Value("${app.auth.basic.deliveryPassword}")
    private String eipPassword = "";

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(eipLogin, eipPassword);
    }
}
