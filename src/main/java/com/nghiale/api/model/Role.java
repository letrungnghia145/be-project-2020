package com.nghiale.api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role extends AbstractModel {
	private static final long serialVersionUID = 3340536082287241801L;
	private String name;
	private String description;

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	private Set<User> users;
}
