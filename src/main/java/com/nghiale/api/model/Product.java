package com.nghiale.api.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractModel {
	private static final long serialVersionUID = 8907993020628938819L;
	private String name;
	private BigDecimal price;
	@Temporal(TemporalType.DATE)
	private Date mfg;
	@Temporal(TemporalType.DATE)
	private Date exp;
	private Long stock;
	@Column(columnDefinition = "text")
	private String description;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
	private Set<Image> images;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
	private Set<Evaluate> evaluates;
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "wishlist")
	private Set<Customer> customers;
}
