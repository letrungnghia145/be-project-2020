package com.nghiale.api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDTO {
	private String productCode;
	private String productName;
	private BigDecimal price;
	private Integer quantity;
}
