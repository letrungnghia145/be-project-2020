package com.nghiale.api.control;

import java.util.List;

import com.nghiale.api.model.CartItem;
import com.nghiale.api.model.Evaluate;
import com.nghiale.api.model.Order;
import com.nghiale.api.model.User;

public interface UserControl {
	public User getUserDetails(Long userID);
	
	public List<User> getAllUsers();

	public User deleteUser(Long userID);

	public User updateUserDetails(User user);

	public List<Order> getCustomerOrders(Long customerID);

	public Order getCustomerOrderDetails(Long customerID, Long orderID);

	public List<CartItem> getCartItems(Long customerID);

	public List<CartItem> addItemToCart(Long customerID, CartItem item);

	public List<CartItem> updateCartItemQuantity(Long customerID, CartItem item);
	
	public List<Evaluate> getAllCustomerEvaluate(Long customerID);
	
	public List<Evaluate> addCustomerEvaluate(Long customerID, Evaluate evaluate);
	
	public List<Evaluate> deleteCustomerEvaluate(Long customerID, Long evaluateID);
	
	public Class<? extends User> getUserClassType(Long userID);
}
