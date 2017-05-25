package com.example.service;

import com.example.domain.Customer;
import com.example.domain.CustomerRepository;
import com.example.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final CustomerRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) {
		Customer customer = repository.findByUserId(userId);
		if(customer == null){
			throw new UsernameNotFoundException(userId);
		}
		
		return new UserDetailsImpl(customer);
	}

}
