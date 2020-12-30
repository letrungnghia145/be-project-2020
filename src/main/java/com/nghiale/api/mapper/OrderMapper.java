package com.nghiale.api.mapper;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiale.api.dto.OrderDTO;
import com.nghiale.api.dto.OrderItemDTO;
import com.nghiale.api.model.Customer;
import com.nghiale.api.model.Order;
import com.nghiale.api.model.OrderItem;
import com.nghiale.api.model.PayMethod;
import com.nghiale.api.utils.Converter;

@Service
public class OrderMapper implements Mapper<Order, OrderDTO> {
	@Autowired
	private Mapper<OrderItem, OrderItemDTO> orderItemMapper;

	@Override
	public Order convertToBO(OrderDTO dto) {
		Order order = new Order();
		Converter.convert(dto, order);
		order.setPayMethod(new PayMethod(dto.getPayMethod()));
		Customer customer = new Customer();
		customer.setUserCode(dto.getUserCode());
		order.setCustomer(customer);
		List<OrderItem> items = orderItemMapper.convertToBOList(dto.getItems());
		order.setItems(Set.copyOf(items));
		return order;
	}

	@Override
	public OrderDTO convertToDTO(Order bo) {
		OrderDTO orderDTO = new OrderDTO();
		Converter.convert(bo, orderDTO);
		orderDTO.setUserCode(bo.getCustomer().getUserCode());
		orderDTO.setPayMethod(bo.getPayMethod().getMethod());
		List<OrderItemDTO> items = orderItemMapper.convertToDTOList(List.copyOf(bo.getItems()));
		orderDTO.setItems(items);
		return orderDTO;
	}

}
