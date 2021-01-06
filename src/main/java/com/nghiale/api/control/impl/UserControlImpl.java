package com.nghiale.api.control.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nghiale.api.control.UserControl;
import com.nghiale.api.entity.ProductEntity;
import com.nghiale.api.entity.UserEntity;
import com.nghiale.api.model.CartItem;
import com.nghiale.api.model.Evaluate;
import com.nghiale.api.model.Order;
import com.nghiale.api.model.Product;
import com.nghiale.api.model.User;

@Service
public class UserControlImpl implements UserControl {
	@Autowired
	private UserEntity userEntity;
	@Autowired
	private ProductEntity productEntity;

	@Override
	public List<User> getAllUsers() {
		return userEntity.findAll();
	}

	@Override
	public User getUser(Long userID) {
		return userEntity.findById(userID).get();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Long userID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserDetails(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Evaluate> getAllEvaluates(Long userID) {
		Optional<User> findById = userEntity.findByIdWithEvaluatesGraph(userID);
		return List.copyOf(findById.get().getEvaluates());
	}

	@Override
	public void deleteEvaluate(Long userID, Long evaluateID) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CartItem> getCart(Long userID) {
		Optional<User> findByIdWithItemsGraph = userEntity.findByIdWithItemsGraph(userID);
		return List.copyOf(findByIdWithItemsGraph.get().getItems());
	}

	@Override
	@Transactional
	public void addItemToCart(Long userID, CartItem item) {
		Product mergeProduct = productEntity.getOne(item.getProduct().getId());
		userEntity.findByIdWithItemsGraph(userID)
				.ifPresent(customer -> customer.addCartItem(mergeProduct, item.getQuantity()));
	}

	@Override
	@Transactional
	public void deleteItemIncart(Long userID, Long productID) {
		Product mergeProduct = productEntity.getOne(productID);
		userEntity.findByIdWithItemsGraph(userID).ifPresent(customer -> customer.removeCartItem(mergeProduct));
	}

	@Override
	@Transactional
	public void updateCartItem(Long userID, Long productID, String action) {
		Product mergeProduct = productEntity.getOne(productID);
		userEntity.findByIdWithItemsGraph(userID).ifPresent(customer -> {
			customer.updateCart(mergeProduct, action);
		});
	}

	@Override
	public List<Order> getAllOrders(Long userID) {
		Optional<User> findByIdWithOrdersGraph = userEntity.findByIdWithOrdersGraph(userID);
		return List.copyOf(findByIdWithOrdersGraph.get().getOrders());
	}

	@Override
	public Order getOrder(Long userID, Long orderID) {
		return userEntity.getOrderDetails(userID, orderID);
	}

//	@Override
//	public void addOrder(Long userID, Order order) {
//		customerEntity.findByIdWithOrdersGraph(userID).ifPresent(customer -> customer.addOrder(order));
//	}

}
