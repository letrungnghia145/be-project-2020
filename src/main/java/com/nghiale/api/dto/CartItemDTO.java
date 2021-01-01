package com.nghiale.api.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemDTO {
	private Long id;
	private String image;
	private String name;
	private String price;
	private Integer quantity;
	private Long customerId;
	private Long productId;
}
