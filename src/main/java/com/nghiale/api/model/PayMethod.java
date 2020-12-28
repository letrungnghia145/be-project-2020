package com.nghiale.api.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.nghiale.api.contants.Method;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PayMethod extends AbstractModel {
	private static final long serialVersionUID = -4934629256149869163L;
	private Method method;
	private String description;

	@OneToMany(mappedBy = "payMethod", orphanRemoval = false)
	private Set<Order> orders;
}
