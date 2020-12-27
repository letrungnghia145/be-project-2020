package com.nghiale.api.mapper;

import java.lang.reflect.Method;
import java.util.function.Predicate;

public interface Mapper<BO, DTO> {

	static final Predicate<Object> predicate = prop -> {
		return prop != null;
	};

	public BO fromDTO(DTO dto);

	public DTO fromBO(BO bo);

	default void map(Object object1, Object object2) {
		Method[] methods = object2.getClass().getDeclaredMethods();
		for (Method method : methods) {
			String name = method.getName();
			if (name.startsWith("get")) {
				try {
					Object prop = method.invoke(object2);
					String setterName = "set".concat(name.substring(3));
					Class<?> returnType = method.getReturnType();
					Method m = predicate.test(prop) ? object1.getClass().getMethod(setterName, returnType) : null;
					if (m != null)
						m.invoke(object1, prop);
				} catch (Exception e) {
				}
			}
		}
	}
}
