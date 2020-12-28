package com.nghiale.api.model;

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
	@JoinTable(name = "wishlist_product", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> wishlist;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private Set<Order> orders;
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Evaluate> evaluates;

	public void addOrder(Order order) {
		this.orders.add(order);
		order.setCustomer(this);
	}

	public void deleteOrder(Order order) {
		this.orders.remove(order);
		order.setCustomer(null);
	}

	public void addEvaluate(Evaluate evaluate) {
		if (evaluate.getValue() >= 1 && evaluate.getValue() <= 5) {
			this.evaluates.add(evaluate);
			evaluate.setCustomer(this);
		}
	}

	public void deleteEvaluate(Evaluate evaluate) {
		this.evaluates.remove(evaluate);
		evaluate.setCustomer(null);
	}
}
