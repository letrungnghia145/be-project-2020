package com.nghiale.api.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("admin")
@Getter
@Setter
@NoArgsConstructor
public class Seller extends User {
	private static final long serialVersionUID = 5320945287681703928L;

}
