package com.nghiale.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class Image extends AbstractModel {
	private static final long serialVersionUID = -7036987774546848193L;
	@EqualsAndHashCode.Include
	private String link;
	private String description;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	public Image(String link, String description) {
		super();
		this.link = link;
		this.description = description;
	}
}
