package com.nghiale.api.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtils {
	public static String randomConfirmationCode() {
		Random random = new Random();
		long nextLong = random.nextInt(999999);
		String format = String.format("%06d", nextLong);
		return format;
	}

	public static String randomUserCode() {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		int s1 = random.nextInt(1000);
		int s2 = random.nextInt(1000);
		int s3 = random.nextInt(1000);
		return builder.append(s1).append(s2).append(s3).toString();
	}

	public static String randomUUIDCode() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
