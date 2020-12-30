package com.nghiale.api.utils;

import java.util.ArrayList;
import java.util.List;

import com.nghiale.api.contants.AppContants;

public class Utilities {

	public static List<String> split(String encoded) {
		String secretKey = AppContants.SECRET_KEY;
		int limit = encoded.length();
		int secretKeyLength = secretKey.length();
		List<String> list = new ArrayList<>();

		for (int start = 0; start < limit; start = start + secretKeyLength) {
			int end = start + secretKeyLength;
			String substring = end <= limit ? encoded.substring(start, end) : encoded.substring(start);
			list.add(substring);
		}
		return list;
	}
}
