package com.nghiale.api.control.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nghiale.api.control.ProductControl;
import com.nghiale.api.entity.ProductEntity;
import com.nghiale.api.model.Evaluate;
import com.nghiale.api.model.Image;
import com.nghiale.api.model.Product;
import com.nghiale.api.utils.ConvertUtils;

@Service
public class ProductControlImpl implements ProductControl {
	@Autowired
	private ProductEntity productEntity;

	@Override
	public List<Product> getAllProducts() {
		return productEntity.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		return productEntity.save(product);
	}

	@Override
	@Transactional
	public Product updateProductDetails(Product product) {
		Optional<Product> findById = productEntity.findById(product.getId());
		findById.ifPresent(bo -> {
			ConvertUtils.convert(product, bo);
		});
		return findById.get();
	}

	@Override
	public Product deleteProduct(Long productID) {
		Optional<Product> findById = productEntity.findById(productID);
		findById.ifPresent(product -> productEntity.delete(product));
		return findById.get();
	}

	@Override
	public Product getProductDetails(Long productID) {
		Optional<Product> findById = productEntity.findById(productID);
		return findById.get();
	}

	@Override
	public List<Image> getAllProductImages(Long productID) {
		List<Image> images = new ArrayList<>();
		productEntity.findByIdWithImagesGraph(productID).ifPresent(product -> {
			product.getImages().forEach(image -> images.add(image));
		});
		return images;
	}

	@Override
	@Transactional
	public List<Image> addProductImages(Long productID, List<Image> images) {
		Optional<Product> findByIdWithImagesGraph = productEntity.findByIdWithImagesGraph(productID);
		findByIdWithImagesGraph.ifPresent(product -> {
			for (Image image : images) {
				product.addImage(image);
			}
		});
		return List.copyOf(findByIdWithImagesGraph.get().getImages());
	}

	@Override
	@Transactional
	public List<Image> deleteProductImage(Long productID, Long imageID) {
		Optional<Product> findByIdWithImagesGraph = productEntity.findByIdWithImagesGraph(productID);
		findByIdWithImagesGraph.ifPresent(product -> {
			Image image = new Image();
			image.setId(imageID);
			product.deleteImage(image);
		});
		return List.copyOf(findByIdWithImagesGraph.get().getImages());
	}

	@Override
	public List<Evaluate> getProductEvaluates(Long productID) {
		List<Evaluate> evaluates = new ArrayList<>();
		productEntity.findByIdWithImagesGraph(productID).ifPresent(product -> {
			product.getEvaluates().forEach(evaluate -> evaluates.add(evaluate));
		});
		return evaluates;
	}

	@Override
	public List<Evaluate> addProductEvaluate(Long productID, Evaluate evaluate) {
		List<Evaluate> evaluates = new ArrayList<>();
		Optional<Product> findByIdWithEvaluatesGraph = productEntity.findByIdWithEvaluatesGraph(productID);
		findByIdWithEvaluatesGraph.ifPresent(product -> product.addEvaluate(evaluate));
		findByIdWithEvaluatesGraph.get().getEvaluates().forEach(evl -> evaluates.add(evl));
		return evaluates;
	}

	@Override
	public Product deleteProductEvaluate(Long productID, Long evaluateID) {
		Optional<Product> findByIdWithEvaluatesGraph = productEntity.findByIdWithEvaluatesGraph(productID);
		findByIdWithEvaluatesGraph.ifPresent(product -> {
			Evaluate evaluate = new Evaluate();
			evaluate.setId(evaluateID);
			product.deleteEvaluate(evaluate);
		});
		return findByIdWithEvaluatesGraph.get();
	}

}
