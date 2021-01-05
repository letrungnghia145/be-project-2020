package com.nghiale.api.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nghiale.api.control.UserControl;
import com.nghiale.api.dto.ProductQuantityWrapper;
import com.nghiale.api.model.CartItem;
import com.nghiale.api.model.Evaluate;
import com.nghiale.api.model.Order;
import com.nghiale.api.model.User;

@RestController
@RequestMapping("/users")
public class UserBoundary {
	@Autowired
	private UserControl control;

	@GetMapping
	public List<User> getAllUsers() {
		return control.getAllUsers();
	}

	@GetMapping("/{uid}")
	public User getUser(@PathVariable("uid") Long userID) {
		return control.getUser(userID);
	}

	@GetMapping("/{cid}/evaluates")
	public List<Evaluate> getAllEvaluates(@PathVariable("cid") Long customerID) {
		return control.getAllEvaluates(customerID);
	}

	@GetMapping("/{cid}/cart-items")
	public List<CartItem> getCart(@PathVariable("cid") Long customerID) {
		return control.getCart(customerID);
	}

	@PostMapping("/{cid}/cart-items")
	public void addItemToCart(@PathVariable("cid") Long customerID, @RequestBody CartItem item) {
		control.addItemToCart(customerID, item);
	}

	@DeleteMapping("/{cid}/cart-items/{iid}")
	public void deleteItemInCart(@PathVariable("cid") Long customerID, @PathVariable("iid") Long itemID) {
		control.deleteItemIncart(customerID, itemID);
	}

	@PutMapping("/{cid}/cart-items/{iid}")
	public void updateCart(@PathVariable("cid") Long customerID, @PathVariable("iid") Long itemID,
			@RequestParam String action) {
		control.updateCartItem(customerID, itemID, action);
	}

	@GetMapping("/{cid}/orders")
	public List<Order> getAllOrders(@PathVariable("cid") Long customerID) {
		return control.getAllOrders(customerID);
	}

	@GetMapping("/{cid}/orders/{oid}")
	public Order getOrderDetails(@PathVariable("cid") Long customerID, @PathVariable("oid") Long orderID) {
		return control.getOrder(customerID, orderID);
	}
}
