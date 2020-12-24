package com.nghiale.api.model;

import javax.persistence.Column;
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
public class Evaluate extends AbstractModel {
	private static final long serialVersionUID = 1040766522531247812L;
	private String comment;
	@Column(columnDefinition = "int(5)")
	private Integer value;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
}