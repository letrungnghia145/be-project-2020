package com.nghiale.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({ @JsonSubTypes.Type(value = Customer.class, name = "CUSTOMER"),
		@JsonSubTypes.Type(value = Seller.class, name = "SELLER"),
		@JsonSubTypes.Type(value = Forwarder.class, name = "FORWARDER"), })
@ToString
public abstract class User extends AbstractModel {
	private static final long serialVersionUID = -5352244872926449891L;
	@Column(unique = true)
	private String userCode;
	private String name;
	@Column(unique = true)
	private String phone;
	private String address;
	@Column(unique = true)
	private String email;
	private String password;
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User(Long id) {
		super(id);
	}

	public void addRole(Role role) {
		roles.add(role);
		role.getUsers().add(this);
	}

	@JsonCreator
	public User(String name, String phone, String address, String email, String password) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.password = password;
	}

}
