package ru.omegastroy.portal.model;


import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "PAYMENT_INFO")
@Data
public class PaymentInfo {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "LAST_CHANGE_TIME")
    private ZonedDateTime lastChange;
}
