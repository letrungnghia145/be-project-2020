package com.nghiale.api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("customer")
@Getter
@Setter
@NoArgsConstructor
@NamedEntityGraph(name = "Customer.orders", attributeNodes = @NamedAttributeNode("orders"))
@NamedEntityGraph(name = "Customer.items", attributeNodes = @NamedAttributeNode("items"))
public class Customer extends User {
	private static final long serialVersionUID = 8993009225947782652L;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private Set<CartItem> items;
	@ManyToMany
	@JoinTable(name = "wishlist_product", joinColumns = @JoinColumn(name = "customer_id"),
	inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> wishlist;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private Set<Order> orders;

	public void addOrder(Order order) {
		this.orders.add(order);
		order.setCustomer(this);
	}

	public void deleteOrder(Order order) {
		this.orders.remove(order);
		order.setCustomer(null);
	}
}
