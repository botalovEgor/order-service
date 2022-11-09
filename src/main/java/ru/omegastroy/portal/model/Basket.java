package ru.omegastroy.portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "BASKET")
@Data
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_sequence")
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "ORDER_ID")
    @OneToOne
    private Order order;

    @OneToMany(mappedBy = "basket")
    private List<BasketItem> items;

    @Column(name = "client_uuid")
    private UUID clientUuid;

}
