package ru.omegastroy.portal.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_STATUS_CATALOG")
@Data
public class OrderStatusCatalog {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;
}
