package com.tistory.heowc.auth.jwt;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.tistory.heowc.domain.Member;


@SuppressWarnings("serial")
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	private String token;
	private Member member;
	
	public JwtAuthenticationToken(String token) {
		super(AuthorityUtils.NO_AUTHORITIES);
		this.token = token;
		this.setAuthenticated(false);
	}

	public JwtAuthenticationToken(Member member, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		this.member = member;
		this.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return member;
	}

	@Override
	public void eraseCredentials() {
		this.eraseCredentials();
		this.token = null;
	}
}
