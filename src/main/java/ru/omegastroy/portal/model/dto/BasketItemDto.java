package ru.omegastroy.portal.model.dto;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class BasketItemDto {
    private Long id;
    private BasketDto basketDto;
    private Long catalogItemId;
    private Integer amount;
    private BigDecimal unitPrice;
    private String unit;
}
