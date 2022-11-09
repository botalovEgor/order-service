package ru.omegastroy.portal.model.dto;


import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PaymentInfoDto {
    private Long id;
    private String paymentStatus;
    private String paymentType;
    private ZonedDateTime lastChange;
}
