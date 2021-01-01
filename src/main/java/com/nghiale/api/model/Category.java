package com.nghiale.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category extends AbstractModel {
	private static final long serialVersionUID = 1206785378514431940L;
	@Column(unique = true)
	private String name;
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
	@JsonIgnore
	private Set<Product> products = new HashSet<>();

	public Category(Long id) {
		this.id = id;
	}
}
