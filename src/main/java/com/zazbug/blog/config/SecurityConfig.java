package com.zazbug.blog.config;

import com.zazbug.blog.security.JwtAuthenticationEntryPoint;
import com.zazbug.blog.security.JwtAuthorizationTokenFilter;
import com.zazbug.blog.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/image/**").permitAll()
				// .antMatchers("/static/**").permitAll()
				.antMatchers("/upload").permitAll()
				.antMatchers(HttpMethod.OPTIONS,"/**").anonymous()
				.anyRequest().authenticated() // 剩下所有都需要验证
				.and()
				.csrf().disable() // 禁用spring security自带的跨域处理
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}


	public PasswordEncoder passwordEncoderBean(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
