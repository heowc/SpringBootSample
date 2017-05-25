package com.tistory.heowc.auth.jwt;

import com.tistory.heowc.auth.BaseAuthenticationToken;
import com.tistory.heowc.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class JwtAuthenticationToken extends BaseAuthenticationToken {

	private String token;
	
	public JwtAuthenticationToken(String token) {
		super(AuthorityUtils.NO_AUTHORITIES);
		this.token = token;
		this.setAuthenticated(false);
	}

	public JwtAuthenticationToken(Member member,
			Collection<? extends GrantedAuthority> authorities) {
		super(member, authorities);
	}
	
	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.token = null;
	}
}
