package com.tistory.heowc.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.tistory.heowc.domain.Member;

@SuppressWarnings("serial")
public class UserDetailsImpl extends User {

	private Member member;
	
	private static final boolean isEnabled				= true;
	private static final boolean isAccountNonExpired	= true;
	private static final boolean isAccountNonLocked		= true;
	private static final boolean isCredentialsNonExpired= true;
	
	public UserDetailsImpl(Member member){
		super(member.getId(), member.getPassoword(), authorities());
		this.member = member;
	}
	
	private static Collection<? extends GrantedAuthority> authorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return authorities;
	}
	
	public Member getMember() {
		return member;
	}
	
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
}