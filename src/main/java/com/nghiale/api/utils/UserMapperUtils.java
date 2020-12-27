package com.nghiale.api.utils;

import java.lang.reflect.Method;
import java.util.function.Predicate;

import com.nghiale.api.model.User;

public class UserMapperUtils {
	public static void map(User dto, User bo) {
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
	}
}
