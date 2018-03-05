package com.tistory.heowc.auth.ajax;

import com.tistory.heowc.auth.UserDetailsImpl;
import com.tistory.heowc.domain.Member;
import com.tistory.heowc.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AjaxUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Member user = repository.findById(username).orElse(null);

		if (user == null) {
			throw new UsernameNotFoundException(username + "라는 사용자가 없습니다.");
		}

		return new UserDetailsImpl(user, AuthorityUtils.createAuthorityList(user.getRole()));
	}
}
