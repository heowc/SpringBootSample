package com.tistory.heowc.component;

import com.tistory.heowc.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetailsImpl extends User {

	public UserDetailsImpl(Member member) {
		super(member.getId(), member.getPassword(), authorities());
	}

	private static Collection<? extends GrantedAuthority> authorities() {
		return AuthorityUtils.createAuthorityList("USER");
	}
}