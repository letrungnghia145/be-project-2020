package com.nghiale.api.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
	private Long id;
	private String productCode;
	private String name;
	private BigDecimal price;
	private Date mfg;
	private Date exp;
	private Long stock;
	private String description;
	private Long category;
	private List<ImageDTO> images;
}
