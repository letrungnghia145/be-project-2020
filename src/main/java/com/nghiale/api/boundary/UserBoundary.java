package com.nghiale.api.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nghiale.api.control.UserControl;
import com.nghiale.api.dto.UserDTO;
import com.nghiale.api.mapper.UserMapper;
import com.nghiale.api.model.Order;
import com.nghiale.api.model.User;

@RestController
@RequestMapping("/users")
public class UserBoundary {
	@Autowired
	private UserControl control;
	@Autowired
	private UserMapper mapper;

	@GetMapping
	public ResponseEntity<?> retrieveAllUsers() {
		List<User> users = control.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(mapper.convertToDTOList(users));
	}

	@GetMapping("/{uid}")
	public ResponseEntity<?> retrieveUser(@PathVariable Long uid) {
		User user = control.getUserDetails(uid);
		return ResponseEntity.status(HttpStatus.OK).body(mapper.convertToDTO(user));
	}

	@DeleteMapping("/{uid}")
	public ResponseEntity<?> deleteUser(@PathVariable Long uid) {
		User user = control.deleteUser(uid);
		return ResponseEntity.status(HttpStatus.OK).body(mapper.convertToDTO(user));
	}

	@PutMapping("/{uid}")
	public ResponseEntity<?> updateUser(@PathVariable Long uid, @RequestBody UserDTO dto) {
		mapper.setUserClassType(control.getUserClassType(uid));
		User user = control.updateUserDetails(mapper.convertToBO(dto));
		return ResponseEntity.status(HttpStatus.OK).body(mapper.convertToDTO(user));
	}

	@GetMapping("/{cid}/orders")
	public ResponseEntity<?> retrieveAllCustomerOrders(@PathVariable Long cid) {
		List<Order> orders = control.getCustomerOrders(cid);
		return ResponseEntity.status(HttpStatus.OK).body(orders);
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
