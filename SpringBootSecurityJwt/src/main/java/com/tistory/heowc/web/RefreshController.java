package com.tistory.heowc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.heowc.auth.jwt.JwtFactory;
import com.tistory.heowc.auth.jwt.JwtInfo;
import com.tistory.heowc.domain.Member;

@RestController
@RequestMapping("/refresh")
public class RefreshController {
	
	@Autowired JwtFactory jwtFactory;
	@Autowired ObjectMapper objectMapper;
	
	@GetMapping
	public ResponseEntity<String> refreshToken(Authentication authentication) {
		System.out.println("refreshToken");
		Member member = (Member)authentication.getPrincipal();
		
		try {
			String token = jwtFactory.refreshToken(objectMapper.writeValueAsString(member));
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add(JwtInfo.HEADER_NAME, token);
			
			return new ResponseEntity<String>("success refresh token", headers, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<String>("fail refresh token", HttpStatus.FORBIDDEN);
		}
	}
}
