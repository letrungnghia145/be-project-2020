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
public class CartItem {
	@EmbeddedId
	private PkCartItem pk;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("customerId")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("productId")
	private Product product;
	@Column(columnDefinition = "int(5)")
	private Long quantity;

	public CartItem(User user, Product product) {
		super();
		this.pk = new PkCartItem(user.getId(), product.getId());
		this.user = user;
		this.product = product;
		this.quantity = 1L;
	}
}
