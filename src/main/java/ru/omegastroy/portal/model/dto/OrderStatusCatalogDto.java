package ru.omegastroy.portal.model.dto;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class OrderStatusCatalogDto {
    private Integer id;
    private String name;
    private String code;
}
