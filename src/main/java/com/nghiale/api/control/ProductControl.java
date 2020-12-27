package com.nghiale.api.control;

import java.util.List;

import com.nghiale.api.model.Evaluate;
import com.nghiale.api.model.Image;
import com.nghiale.api.model.Product;

public interface ProductControl {
	public List<Product> getAllProducts();

	public Product addProduct(Product product);

	public Product updateProductDetails(Product product);

	public Product deleteProduct(Long productID);

	public Product getProductDetails(Long productID);

	public List<Image> getProductImages(Long productID);

	public Product addProductImage(Long productID, Image image);

	public Product deleteProductImage(Long productID, Long imageID);

	public List<Evaluate> getProductEvaluates(Long productID);

	public List<Evaluate> addProductEvaluate(Long productID, Evaluate evaluate);

	public Product deleteProductEvaluate(Long productID, Long evaluateID);
}
