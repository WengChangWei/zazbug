package com.zazbug.blog.security;

import com.zazbug.blog.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

public class SecurityUserDetails extends User implements UserDetails {

	private Collection<? extends GrantedAuthority> authorities;

	public SecurityUserDetails(String userName, Collection<? extends GrantedAuthority> authorities){
		this.authorities = authorities;
		this.setUsername(userName);
		String encode = new BCryptPasswordEncoder().encode("123456");
		this.setPassword(encode);
		this.setAuthorities(authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
