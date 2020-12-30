package com.nghiale.api;

import java.util.Map;

import com.nghiale.api.utils.TokenUtils;

public class Test {
	public static void main(String[] args) throws Exception {
		Map<String, Object> infomationFromToken = TokenUtils
				.getInfomationFromToken("eyb2IjY2ZSInbHIjdmdWfQJjRloi9kIsZhVloiFsUi==");
		Object object = infomationFromToken.get("code");
		System.out.println(object);
	}
}
