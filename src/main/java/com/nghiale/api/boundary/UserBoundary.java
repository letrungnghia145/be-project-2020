package com.nghiale.api.boundary;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nghiale.api.control.UserControl;
import com.nghiale.api.dto.OrderDTO;
import com.nghiale.api.dto.RequestTokenCodeWrapper;
import com.nghiale.api.dto.UserDTO;
import com.nghiale.api.mapper.Mapper;
import com.nghiale.api.mapper.UserMapper;
import com.nghiale.api.model.Order;
import com.nghiale.api.model.User;
import com.nghiale.api.utils.TimeUtils;
import com.nghiale.api.utils.TokenUtils;

@RestController
@RequestMapping("/users")
public class UserBoundary {
	@Autowired
	private UserControl control;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private Mapper<Order, OrderDTO> orderMapper;
	@Autowired
	private ObjectMapper jsonMapper;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody RequestTokenCodeWrapper wrapper)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> fromToken = TokenUtils.getInfomationFromToken(wrapper.getToken());
		Date tokenExpriedDate = new Date((Long) fromToken.get("tokenExpriedDate"));
		boolean isExpiredToken = TimeUtils.isExpired(tokenExpriedDate);
		boolean isValidCode = wrapper.getCode().equals((String) fromToken.get("code"));
		if (isValidCode && !isExpiredToken) {
			return ResponseEntity.status(HttpStatus.OK).body("OK");
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(!isValidCode ? "Invalid code!" : "Expired token!");
	}

	@GetMapping
	public ResponseEntity<?> retrieveAllUsers() {
		List<User> users = control.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(userMapper.convertToDTOList(users));
	}

	@GetMapping("/{uid}")
	public ResponseEntity<?> retrieveUser(@PathVariable Long uid) {
		User user = control.getUserDetails(uid);
		return ResponseEntity.status(HttpStatus.OK).body(userMapper.convertToDTO(user));
	}

	@DeleteMapping("/{uid}")
	public ResponseEntity<?> deleteUser(@PathVariable Long uid) {
		User user = control.deleteUser(uid);
		return ResponseEntity.status(HttpStatus.OK).body(userMapper.convertToDTO(user));
	}

	@PutMapping("/{uid}")
	public ResponseEntity<?> updateUserDetails(@PathVariable Long uid, @RequestBody UserDTO dto) {
		userMapper.setUserClassType(control.getUserClassType(uid));
		User convertToBO = userMapper.convertToBO(dto);
		convertToBO.setId(uid);
		User user = control.updateUserDetails(convertToBO);
		return ResponseEntity.status(HttpStatus.OK).body(userMapper.convertToDTO(user));
	}

	@GetMapping("/{cid}/orders")
	public ResponseEntity<?> retrieveAllCustomerOrders(@PathVariable Long cid) {
		List<Order> orders = control.getCustomerOrders(cid);
		return ResponseEntity.status(HttpStatus.OK).body(orderMapper.convertToDTOList(orders));
	}

	@GetMapping("/{cid}/orders/{oid}")
	public ResponseEntity<?> retrieveCustomerOrder(@PathVariable Long cid, @PathVariable Long oid) {
		Order order = control.getCustomerOrderDetails(cid, oid);
		return ResponseEntity.status(HttpStatus.OK).body(orderMapper.convertToDTO(order));
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
