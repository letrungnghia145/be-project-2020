package com.nghiale.api.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
	@EmbeddedId
	private PkOrderItem pk;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("orderId")
	private Order order;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("productId")
	private Product product;
	@Column(columnDefinition = "int(5)")
	private Integer quantity;

	public OrderItem(Order order, Product product, Integer quantity) {
		super();
		this.pk = new PkOrderItem(order.getId(), product.getId());
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}
}
