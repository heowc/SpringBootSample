package com.tistory.heowc.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tistory.heowc.domain.Member;
import com.tistory.heowc.repository.MemberRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepository repository;
	
	@Override
	public UserDetailsImpl loadUserByUsername(String username) {
		
		Member user = repository.findOne(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username + "라는 사용자가 없습니다.");
		}
		
		return new UserDetailsImpl(user, AuthorityUtils.createAuthorityList(user.getRole()));
	}

}
