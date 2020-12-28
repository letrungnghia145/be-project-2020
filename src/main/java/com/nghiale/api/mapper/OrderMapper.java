package com.nghiale.api.mapper;

import com.nghiale.api.dto.OrderDTO;
import com.nghiale.api.model.Customer;
import com.nghiale.api.model.Order;
import com.nghiale.api.utils.ConvertUtils;

public class OrderMapper implements Mapper<Order, OrderDTO> {

	@Override
	public Order convertToBO(OrderDTO dto) {
		Order order = new Order();
		ConvertUtils.convert(dto, order);
		return order;
	}

	@Override
	public OrderDTO convertToDTO(Order bo) {
		OrderDTO orderDTO = new OrderDTO();
		ConvertUtils.convert(bo, orderDTO);
		Customer customer = bo.getCustomer();
//		orderDTO.setCustomerName(customer.getName());
//		orderDTO.setCustomerPhone(customer.get);
		return orderDTO;
	}

}
