package com.nghiale.api.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"order\"")
@Getter
@Setter
@NoArgsConstructor
public class Order extends AbstractModel {
	private static final long serialVersionUID = 560226111253281070L;
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	private BigDecimal total;
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	@OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<OrderItem> items;
}
