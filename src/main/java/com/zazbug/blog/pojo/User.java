package com.zazbug.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

@Table(name = "df_user")
public class User {

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "lastLoginTime")
	private Date lastLoginTime;

	@Column(name = "lastLoginIp")
	private String lastLoginIp;

	@Column(name = "headPic")
	private String headPic;

	private Collection<? extends GrantedAuthority> authorities;



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
}
