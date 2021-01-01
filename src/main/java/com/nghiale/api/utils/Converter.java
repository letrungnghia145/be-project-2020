package com.nghiale.api.utils;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;

public class Converter {
	private static final Predicate<Object> PREDICATE = value -> {
		return (value != null);
	};

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void convert(Object from, Object to) {
		Class<? extends Object> fromClazz = from.getClass();
		Class<? extends Object> toclazz = to.getClass();
		Method[] methods = fromClazz.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("get")) {
				try {
					Object prop = method.invoke(from);
					boolean isNonNullProp = PREDICATE.test(prop);
					String setterName = "set".concat(methodName.substring(3));
					Class<?> returnType = method.getReturnType();
					if (returnType.equals(Set.class)) {
						Set fromSet = (Set) method.invoke(from);
						Set toSet = (Set) method.invoke(to);
						fromSet.forEach(object -> {
							if (!toSet.contains(object)) {
								toSet.add(object);
							} else {
								toSet.forEach(bo -> {
									if (bo.equals(object)) convert(object, bo);
								});
							}
						});
					} else {
						Method setter = isNonNullProp ? toclazz.getMethod(setterName, returnType) : null;
						if (setter != null)
							setter.invoke(to, prop);
					}
				} catch (Exception e) {
				}
			}
		}
	}
}
