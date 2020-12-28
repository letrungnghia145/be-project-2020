package com.nghiale.api.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OrderDTO {
	private Date purchaseDate;
	private BigDecimal total;
	private String consigneeName;
	private String consigneePhone;
	private String address;
	private List<OrderItemDTO> items;
}
