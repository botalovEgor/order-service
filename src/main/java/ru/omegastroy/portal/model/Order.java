package ru.omegastroy.portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ORDER")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CLIENT_UUID")
    private UUID clientUUID;

    @JoinColumn(name = "STATUS_ID")
    @ManyToOne
    private OrderStatusCatalog status;

    @JoinColumn(name = "BASKET_ID")
    @OneToOne
    private Basket basket;

    @JoinColumn(name = "PAYMENT_INFO_ID")
    @OneToOne
    private PaymentInfo paymentInfo;

    @JoinColumn(name = "DELIVERY_INFO_ID")
    @OneToOne
    private DeliveryInfo deliveryInfo;
}
