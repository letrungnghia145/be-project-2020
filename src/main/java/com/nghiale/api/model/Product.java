package com.nghiale.api.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nghiale.api.dto.ImageDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedEntityGraph(name = "Product.graph", attributeNodes = { @NamedAttributeNode("category"),
		@NamedAttributeNode("images"), @NamedAttributeNode("evaluates") })
@JsonIgnoreProperties(value = { "evaluates", "customers" })
public class Product extends AbstractModel {
	private static final long serialVersionUID = 8907993020628938819L;
	@NaturalId
	private String productCode;
	private String name;
	private BigDecimal price;
	@Temporal(TemporalType.DATE)
	private Date mfg;
	@Temporal(TemporalType.DATE)
	private Date exp;
	private Long stock;
	@Column(columnDefinition = "text")
	private String description;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
	private Set<Image> images;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
	private Set<Evaluate> evaluates;
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "wishlist")
	private Set<Customer> customers;

	public void addImage(Image image) {
		this.images.add(image);
		image.setProduct(this);
	}

	public void deleteImage(Image image) {
		this.images.remove(image);
		image.setProduct(null);
	}

	public void addEvaluate(Evaluate evaluate) {
		this.evaluates.add(evaluate);
		evaluate.setProduct(this);
	}

	public void deleteEvaluate(Evaluate evaluate) {
		this.evaluates.remove(evaluate);
		evaluate.setProduct(null);
	}

	public void setCategory(Category category) {
		this.category = category;
		category.getProducts().add(this);
	}

	public void setImages(List<ImageDTO> images) {
		if (images != null) {
			this.images = new HashSet<>();
			images.forEach(dtoImage -> {
				Image image = new Image();
				image.setLink(dtoImage.getLink());
				String dtoDes = dtoImage.getDescription();
				image.setDescription(dtoDes.equals("default") ?("Image of product " + this.getName()):dtoDes);
				this.addImage(image);
			});
		}
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public void setCategory(Long id) {
		if (id != null) {
			Category category = new Category(id);
			this.setCategory(category);
		}
	}
}
