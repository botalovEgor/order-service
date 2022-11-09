package ru.omegastroy.portal.model.dto;


import lombok.Data;

import java.time.ZonedDateTime;


@Data
public class DeliveryInfoDto {
    private Long id;
    private String deliveryStatus;
    private String deliveryType;
    private ZonedDateTime startInterval;
    private ZonedDateTime endInterval;
    private String address;
    private String contactNumber;
    private String contactName;
    private String additionalInfo;
}
