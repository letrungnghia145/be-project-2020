package com.nghiale.api.boundary;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserBoundary {
	@GetMapping
	public void retrieveAllUsers() {

	}

	@GetMapping("/{uid}")
	public void retrieveUser(@PathVariable Long uid) {

	}

	@DeleteMapping("/{uid}")
	public void deleteUser(@PathVariable Long uid) {

	}

	@PutMapping("/{uid}")
	public void updateUser(@PathVariable Long uid) {

	}

	@GetMapping("/{cid}/orders")
	public void retrieveAllCustomerOrders(@PathVariable Long cid) {

	}

	@GetMapping("/{cid}/orders/{oid}")
	public void retrieveCustomerOrder(@PathVariable Long cid, @PathVariable Long oid) {

	}

	@GetMapping("/{cid}/cart-items")
	public void retrieveAllCustomerCartItems(@PathVariable Long cid) {

	}

	@PostMapping("/{cid}/cart-items")
	public void createCustomerCartItem(@PathVariable Long cid) {

	}

	@PutMapping("/{cid}/cart-items/{iid}")
	public void updateCustomerCartItem(@PathVariable Long cid, @PathVariable Long iid) {

	}

	@DeleteMapping("/{cid}/cart-items/{iid}")
	public void deleteCustomerCartItem(@PathVariable Long cid, @PathVariable Long iid) {

	}
}
