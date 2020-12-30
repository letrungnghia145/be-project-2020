package com.nghiale.api.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nghiale.api.contants.Method;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OrderDTO {
	private String orderCode;
	private Date purchaseDate;
	private BigDecimal total;
	private String consigneeName;
	private String consigneePhone;
	private String address;
	private String userCode;
	private List<OrderItemDTO> items;
	private Method payMethod;
}
