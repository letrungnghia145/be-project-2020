package com.nghiale.api.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Forwarder extends User{
	private static final long serialVersionUID = 8168422603287621132L;

}
