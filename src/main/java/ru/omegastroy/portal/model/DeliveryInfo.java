package ru.omegastroy.portal.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "DELIVERY_INFO")
@Data
public class DeliveryInfo {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DELIVERY_STATUS")
    private String deliveryStatus;

    @Column(name = "DELIVERY_TYPE")
    private String deliveryType;

    @Column(name = "START_INTERVAL")
    private ZonedDateTime startInterval;

    @Column(name = "END_INTERVAL")
    private ZonedDateTime endInterval;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "CONTACT_NAME")
    private String contactName;

    @Column(name = "ADDITIONAL_INFO")
    private String additionalInfo;
}
