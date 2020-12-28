package com.nghiale.api.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long id;
	private String name;
	private BigDecimal price;
	@Temporal(TemporalType.DATE)
	private Date mfg;
	@Temporal(TemporalType.DATE)
	private Date exp;
	private Long stock;
	private String description;
	private Long category;
	private List<ImageDTO> images;
}
