package com.tistory.heowc.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tistory.heowc.domain.Member;

public class UserDetailsImpl implements UserDetails {

	private Member member;
	private List<GrantedAuthority> authorities;
	
	private Boolean accountNonExpired = false;
	private Boolean accountNonLocked  = false;
	private Boolean credentialsNonExpired = false;
	private Boolean enabled = false;

	public UserDetailsImpl(Member member, List<GrantedAuthority> authorities) {
		this.member = member;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Member getMember() {
		return member;
	}
	
	@Override
	public String getPassword() {
		return "";
	}

	@Override
	public String getUsername() {
		return member.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
