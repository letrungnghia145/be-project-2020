package com.nghiale.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image extends AbstractModel {
	private static final long serialVersionUID = -7036987774546848193L;
	private String link;
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
}
