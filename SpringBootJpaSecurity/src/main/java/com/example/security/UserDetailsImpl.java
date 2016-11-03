package com.example.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.domain.Customer;

public class UserDetailsImpl extends User {

	private static final long serialVersionUID = -1716284716411894843L;

	public UserDetailsImpl(Customer customer){
		super(customer.getUserId(), customer.getUserPassword(), authorities());
	}
	
	private static Collection<? extends GrantedAuthority> authorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority("USER"));
		return authorities;
	}
}
