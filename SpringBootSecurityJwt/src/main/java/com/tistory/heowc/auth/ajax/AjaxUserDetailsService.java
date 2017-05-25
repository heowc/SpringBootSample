package com.tistory.heowc.auth.ajax;

import com.tistory.heowc.auth.UserDetailsImpl;
import com.tistory.heowc.domain.Member;
import com.tistory.heowc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AjaxUserDetailsService implements UserDetailsService {

	private final MemberRepository repository;
	
	@Override
	public UserDetailsImpl loadUserByUsername(String username) {
		
		Member user = repository.findOne(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username + "라는 사용자가 없습니다.");
		}
		
		return new UserDetailsImpl(user, AuthorityUtils.createAuthorityList(user.getRole()));
	}
}
