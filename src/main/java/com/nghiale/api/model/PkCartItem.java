package com.nghiale.api.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class PkCartItem implements Serializable {
	private static final long serialVersionUID = -642094178462061798L;
	private Long customerId;
	private Long productId;
}
