package com.nghiale.api.boundary;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nghiale.api.contants.AppContants;
import com.nghiale.api.dto.ResponseTokenWrapper;
import com.nghiale.api.dto.UserDTO;
import com.nghiale.api.utils.RandomUtils;
import com.nghiale.api.utils.TokenUtils;

@RestController
public class AppControlBoundary {

	@PostMapping("/registry")
	public ResponseEntity<?> registry(@RequestBody UserDTO dto) throws JsonProcessingException {
		String code = RandomUtils.random();
		System.out.println(code);
		Map<String, Object> info = new HashMap<>();
		info.put("userDTO", dto);
		info.put("code", code);
		info.put("tokenExpriedDate", new Date(System.currentTimeMillis() + AppContants.expiredRegistryTokenTime));
		String generatedToken = TokenUtils.generateToken(info);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseTokenWrapper(generatedToken));
	}
}
