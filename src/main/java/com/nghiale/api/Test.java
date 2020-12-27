package com.nghiale.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Predicate;

import com.nghiale.api.model.Customer;
import com.nghiale.api.model.User;

public class Test {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		User dto = new Customer();
		dto.setName("Le Trung Nghia");
		dto.setEmail("nghia1k45@gmail.com");
		dto.setAddress("KTX Khu B");
		dto.setPassword("174256443");

		User bo = new Customer();
		bo.setName("Le Trung Nghia");
		bo.setEmail("pyn_xd_2k@yahoo.com");
		bo.setAddress("Tay Ninh");
		bo.setId(1L);
		bo.setPassword("172285633");
		bo.setPhone("0868880758");

		User newObj = setProperties(dto, bo);
		System.out.println(newObj);
	}

	public static User setProperties(User dto, User bo) {
		Predicate<Object> predicate = object -> {
			return object != null;
		};
		try {
			Method[] methods = User.class.getDeclaredMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if (methodName.startsWith("get")) {
					Object dtoProp = method.invoke(dto);
					boolean isNonNullProp = predicate.test(dtoProp);
					String setterName = "set" + methodName.substring(3);
					Method boSetter = isNonNullProp ? User.class.getMethod(setterName, method.getReturnType()) : null;
					if (boSetter != null) boSetter.invoke(bo, dtoProp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}
}
