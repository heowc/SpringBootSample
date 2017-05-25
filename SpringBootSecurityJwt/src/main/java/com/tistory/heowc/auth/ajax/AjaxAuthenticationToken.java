package com.tistory.heowc.auth.ajax;

import com.tistory.heowc.auth.BaseAuthenticationToken;
import com.tistory.heowc.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class AjaxAuthenticationToken extends BaseAuthenticationToken {

	private String id;
	
	public AjaxAuthenticationToken(String id) {
		super(AuthorityUtils.NO_AUTHORITIES);
		this.id = id;
		this.setAuthenticated(false);
	}
	
	public AjaxAuthenticationToken(Member member,
			Collection<? extends GrantedAuthority> authorities) {
		super(member, authorities);
	}
	
	@Override
	public Object getCredentials() {
		return id;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.id = null;
	}
}
