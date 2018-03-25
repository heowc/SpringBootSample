package com.tistory.heowc.service;

import com.tistory.heowc.component.UserDetailsImpl;
import com.tistory.heowc.domain.Member;
import com.tistory.heowc.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepository repository;

	@Override
	public UserDetails loadUserByUsername(String id) {
		Member member = repository.findById(id)
								.orElseThrow(() -> new UsernameNotFoundException(id));

		return new UserDetailsImpl(member);
	}
}