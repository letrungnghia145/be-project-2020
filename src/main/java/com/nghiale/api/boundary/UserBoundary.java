package com.nghiale.api.boundary;

import java.util.Date;
import java.util.HashSet;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nghiale.api.control.UserControl;
import com.nghiale.api.dto.CartItemDTO;
import com.nghiale.api.dto.OrderDTO;
import com.nghiale.api.dto.RequestTokenCodeWrapper;
import com.nghiale.api.dto.UserDTO;
import com.nghiale.api.dto.UserRegisterDTO;
import com.nghiale.api.exception.ConfirmationCodeError;
import com.nghiale.api.mapper.Mapper;
import com.nghiale.api.mapper.UserMapper;
import com.nghiale.api.model.Customer;
import com.nghiale.api.model.Order;
import com.nghiale.api.model.Role;
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
	//need secret key in token to compare (validate token is server send to client)
	public ResponseEntity<?> createUser(@RequestBody RequestTokenCodeWrapper wrapper) throws Exception {
		Map<String, Object> fromToken = TokenUtils.getInfomationFromToken(wrapper.getToken());
		Date tokenExpriedDate = new Date((Long) fromToken.get("tokenExpriedDate"));
		boolean isExpiredToken = TimeUtils.isExpired(tokenExpriedDate);
		boolean isValidCode = wrapper.getCode().equals((String) fromToken.get("code"));
		if (isValidCode && !isExpiredToken) {
			userMapper.setUserClassType(Customer.class);
			User user = userMapper
					.convertToBO(jsonMapper.convertValue(fromToken.get("userDTO"), UserRegisterDTO.class));
			user.setRoles(new HashSet<>());
			user.addRole(new Role(3L));
			User addUser = control.addUser(user);
			return ResponseEntity.status(HttpStatus.OK).body(userMapper.convertToDTO(addUser));
		}
		throw new ConfirmationCodeError(!isValidCode ? "Invalid code!" : "Expired token!");
	}

	@GetMapping
	public List<UserDTO> retrieveAllUsers() {
		List<User> users = control.getAllUsers();
		return userMapper.convertToDTOList(users);
	}

	@GetMapping("/{uid}")
	public UserDTO retrieveUser(@PathVariable Long uid) {
		User user = control.getUserDetails(uid);
		return userMapper.convertToDTO(user);
	}

	@DeleteMapping("/{uid}")
	public UserDTO deleteUser(@PathVariable Long uid) {
		User user = control.deleteUser(uid);
		return userMapper.convertToDTO(user);
	}

	@PutMapping("/{uid}")
	public UserDTO updateUserDetails(@PathVariable Long uid, @RequestBody UserDTO dto) {
		userMapper.setUserClassType(control.getUserClassType(uid));
		User convertToBO = userMapper.convertToBO(dto);
		convertToBO.setId(uid);
		User user = control.updateUserDetails(convertToBO);
		return userMapper.convertToDTO(user);
	}

	@GetMapping("/{cid}/orders")
	public List<OrderDTO> retrieveAllCustomerOrders(@PathVariable Long cid) {
		List<Order> orders = control.getCustomerOrders(cid);
		return orderMapper.convertToDTOList(orders);
	}

	@GetMapping("/{cid}/orders/{oid}")
	public OrderDTO retrieveCustomerOrder(@PathVariable Long cid, @PathVariable Long oid) {
		Order order = control.getCustomerOrderDetails(cid, oid);
		return orderMapper.convertToDTO(order);
	}

	@GetMapping("/{cid}/cart-items")
	public void retrieveAllCustomerCartItems(@PathVariable Long cid) {
	}

	@PostMapping("/{cid}/cart-items")
	public CartItemDTO createCustomerCartItem(@PathVariable Long cid, @RequestBody CartItemDTO cartItemDTO) {
		System.out.println(cartItemDTO);
		return cartItemDTO;
	}

	@PutMapping("/{cid}/cart-items/{iid}")
	public void updateCustomerCartItem(@PathVariable Long cid, @PathVariable Long iid) {
		
	}

	@DeleteMapping("/{cid}/cart-items/{iid}")
	public void deleteCustomerCartItem(@PathVariable Long cid, @PathVariable Long iid) {

	}
}
