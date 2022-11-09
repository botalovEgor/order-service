package ru.omegastroy.portal.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "BASKET_ITEM")
@Data
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_item_sequence")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BASKET_ID")
    private Basket basket;

    @Column(name = "CATALOG_ITEM_ID")
    private Long catalogItemId;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column (name = "unit")
    private String unit;
}
