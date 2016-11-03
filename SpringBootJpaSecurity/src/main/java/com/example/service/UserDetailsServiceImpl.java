package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;
import com.example.domain.CustomerRepository;
import com.example.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) {
		Customer customer = repository.findByUserId(userId);
		if(customer == null){
			throw new UsernameNotFoundException(userId);
		}
		
		return new UserDetailsImpl(customer);
	}

}
