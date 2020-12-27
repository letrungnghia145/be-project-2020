package com.nghiale.api.control.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiale.api.control.UserControl;
import com.nghiale.api.entity.CustomerEntity;
import com.nghiale.api.entity.UserEntity;
import com.nghiale.api.model.CartItem;
import com.nghiale.api.model.Order;
import com.nghiale.api.model.User;
import com.nghiale.api.utils.UserMapperUtils;

@Service
public class UserControlImpl implements UserControl {
	@Autowired
	private UserEntity<User> userEntity;
	@Autowired
	private CustomerEntity customerEntity;

	@Override
	public User getUserDetails(Long userID) {
		Optional<User> findById = userEntity.findById(userID);
		return findById.get();
	}

	@Override
	public User deleteUser(Long userID) {
		Optional<User> findById = userEntity.findById(userID);
		findById.ifPresent(user -> userEntity.delete(user));
		return findById.get();
	}

	@Override
	public User updateUserDetails(User user) {
		Optional<User> findById = userEntity.findById(user.getId());
		findById.ifPresent(bo -> UserMapperUtils.map(user, bo));
		return findById.get();
	}

	@Override
	public List<Order> getCustomerOrders(Long customerID) {
		List<Order> orders = new ArrayList<>();
		customerEntity.findByIdWithOrdersGraph(customerID).ifPresent(customer -> {
			customer.getOrders().forEach(order -> orders.add(order));
		});
		return orders;
	}

	@Override
	public Order getCustomerOrderDetails(Long customerID, Long orderID) {
		for (Order order : getCustomerOrders(customerID)) {
			if (order.getId().equals(orderID)) {
				return order;
			}
		}
		return null;
	}

	@Override
	public List<CartItem> getCartItems(Long customerID) {
		List<CartItem> items = new ArrayList<>();
		customerEntity.findByIdWithItemsGraph(customerID).ifPresent(customer -> {
			customer.getItems().forEach(item -> items.add(item));
		});
		return items;
	}

	@Override
	public List<CartItem> addItemToCart(Long customerID, CartItem item) {
		return null;
	}

	@Override
	public List<CartItem> updateCartItemQuantity(Long customerID, CartItem item) {
		// TODO Auto-generated method stub
		return null;
	}

}
