package com.tistory.heowc.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

//	@Secured({"USER"})
//	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String user(Authentication authentication) {
		System.out.println(authentication.getPrincipal());
		System.out.println(authentication.getAuthorities().toString());
		return "I'm Jwt Token User!";
	}
	
//	@Secured({"ADMIN"})
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String admin(Authentication authentication) {
		System.out.println(authentication.getPrincipal());
		System.out.println(authentication.getAuthorities().toString());
		return "I'm Jwt Token Admin!";
	}
}
