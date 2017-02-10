package com.tistory.heowc.auth.ajax;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.tistory.heowc.domain.Member;


@SuppressWarnings("serial")
public class AjaxAuthenticationToken extends AbstractAuthenticationToken {

	private String id;
	private Member member;
	
	public AjaxAuthenticationToken(String id) {
		super(AuthorityUtils.NO_AUTHORITIES);
		this.id = id;
		this.setAuthenticated(false);
	}

	public AjaxAuthenticationToken(Member member, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		this.member = member;
		this.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return id;
	}

	@Override
	public Object getPrincipal() {
		return member;
	}

	@Override
	public void eraseCredentials() {
		this.eraseCredentials();
		this.id = null;
	}
}
