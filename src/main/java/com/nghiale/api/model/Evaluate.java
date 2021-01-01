package com.nghiale.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Evaluate extends AbstractModel {
	private static final long serialVersionUID = 1040766522531247812L;
	@Column(unique = true)
	private String evaluateCode;
	private String comment;
	@Column(columnDefinition = "int(5)")
	private Integer value;

	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;

	public Evaluate(String comment, Integer value) {
		super();
		this.comment = comment;
		this.value = value;
	}

	public Evaluate(Long id) {
		super(id);
	}

}
