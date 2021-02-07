package com.zazbug.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

@Data
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

	@Column(name = "introduce")
	private String introduce;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "email")
	private String email;

	@Column(name = "qq")
	private String qq;

	@Column(name = "weibo")
	private String weibo;

	private Collection<? extends GrantedAuthority> authorities;

}
