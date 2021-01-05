package com.nghiale.api.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nghiale.api.contants.UpdateCartAction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("customer")
@Getter
@Setter
@NoArgsConstructor
@NamedEntityGraph(name = "Customer.orders", attributeNodes = @NamedAttributeNode(value = "orders", subgraph = "Order.graph"), subgraphs = {
		@NamedSubgraph(name = "Order.graph", attributeNodes = { @NamedAttributeNode("payMethod"),
				@NamedAttributeNode(value = "items", subgraph = "OrderItem.graph") }),
		@NamedSubgraph(name = "OrderItem.graph", attributeNodes = @NamedAttributeNode("product")) })
@NamedEntityGraph(name = "Customer.items", attributeNodes = @NamedAttributeNode(value = "items", subgraph = "items.graph"), subgraphs = {
		@NamedSubgraph(name = "items.graph", attributeNodes = {
				@NamedAttributeNode(value = "product", subgraph = "product.graph") }),
		@NamedSubgraph(name = "product.graph", attributeNodes = @NamedAttributeNode("images")) })
//@NamedEntityGraph(name = "Customer.evaluates")
@JsonIgnoreProperties({ "roles", "items", "wishlist", "orders", "evaluates" })
public class Customer extends User {
	private static final long serialVersionUID = 8993009225947782652L;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
	private Set<CartItem> items;
	@ManyToMany
	@JoinTable(name = "wishlist_product", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> wishlist;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private Set<Order> orders;
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Evaluate> evaluates = new HashSet<>();

	public Customer(Long id) {
		super(id);
	}

	@JsonCreator
	public Customer(String name, String phone, String address, String email, String password) {
		super(name, phone, address, email, password);
	}

	public void addCartItem(Product product, Long quantity) {
		CartItem cartItem = new CartItem(this, product, quantity);
		if (!this.items.contains(cartItem)) {
			this.items.add(cartItem);
			return;
		}
		items.forEach(item -> {
			if (item.equals(cartItem))
				item.increaseQuantity(quantity);
		});
	}

	public void updateCart(Product product, String action) {
		for (Iterator<CartItem> iterator = this.items.iterator(); iterator.hasNext();) {
			CartItem item = iterator.next();
			if (item.getProduct().equals(product)) {
				switch (action) {
				case "increase":
					item.increaseQuantity(1L);
					break;
				case "decrease":
					if (item.getQuantity() > 1) {
						item.decreaseQuantity(1L);
						return;
					}
					iterator.remove();
					break;
				default:
					break;
				}
			}
		}
	}

	public void removeCartItem(Product product) {
		for (Iterator<CartItem> iterator = this.items.iterator(); iterator.hasNext();) {
			CartItem item = iterator.next();
			if (item.getProduct().equals(product)) {
				iterator.remove();
			}
		}
	}

	public void addOrder(Order order) {
		this.orders.add(order);
		order.setCustomer(this);
	}

}
