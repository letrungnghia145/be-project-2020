package com.nghiale.api.utils;

import java.util.Random;

public class RandomUtils {
	public static String random() {
		Random random = new Random();
		long nextLong = random.nextInt(999999);
		String format = String.format("%06d", nextLong);
		return format;
	}
}
