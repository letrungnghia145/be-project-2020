package com.nghiale.api.mapper;

import org.springframework.stereotype.Service;

import com.nghiale.api.dto.OrderItemDTO;
import com.nghiale.api.model.OrderItem;
import com.nghiale.api.model.Product;

@Service
public class OrderItemMapper implements Mapper<OrderItem, OrderItemDTO> {

	@Override
	public OrderItem convertToBO(OrderItemDTO dto) {
		OrderItem orderItem = new OrderItem();
		Product product = new Product();
		product.setProductCode(dto.getProductCode());
		orderItem.setProduct(product);
		orderItem.setQuantity(dto.getQuantity());
		return orderItem;
	}

	@Override
	public OrderItemDTO convertToDTO(OrderItem bo) {
		OrderItemDTO orderItemDTO = new OrderItemDTO();
		Product product = bo.getProduct();
		orderItemDTO.setPrice(product.getPrice());
		orderItemDTO.setProductCode(product.getProductCode());
		orderItemDTO.setProductName(product.getName());
		orderItemDTO.setQuantity(bo.getQuantity());
		return orderItemDTO;
	}

}
