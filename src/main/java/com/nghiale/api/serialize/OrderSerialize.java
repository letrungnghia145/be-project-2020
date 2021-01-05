package com.nghiale.api.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.nghiale.api.model.Order;

public class OrderSerialize extends StdSerializer<Order> {

	private static final long serialVersionUID = 7000458020726245082L;

	protected OrderSerialize(Class<Order> t) {
		super(t);
	}

	public OrderSerialize() {
		this(null);
	}

	@Override
	public void serialize(Order value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeObjectField("orderCode", value.getOrderCode());
		gen.writeObjectField("purchaseDate", value.getPurchaseDate());
		gen.writeObjectField("total", value.getTotal());
		gen.writeObjectField("consigneeName", value.getConsigneeName());
		gen.writeObjectField("consigneePhone", value.getConsigneePhone());
		gen.writeObjectField("address", value.getAddress());
		gen.writeObjectField("userCode", value.getCustomer() != null ? value.getCustomer().getId() : null);
		gen.writeObjectField("items", value.getItems());
		gen.writeEndObject();
	}
}
