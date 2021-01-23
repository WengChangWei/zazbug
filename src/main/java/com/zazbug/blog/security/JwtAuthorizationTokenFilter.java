package com.zazbug.blog.security;

import com.zazbug.blog.entity.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

	private final UserDetailsService userDetailsService;
	private final JwtTokenUtils jwtTokenUtils;
	private final String tokenHeader;

	public JwtAuthorizationTokenFilter(@Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService, JwtTokenUtils jwtTokenUtils, @Value("${jwt.token}") String tokenHeader){
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtils = jwtTokenUtils;
		this.tokenHeader = tokenHeader;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		final String requestHeader = httpServletRequest.getHeader(this.tokenHeader);
		String username = null;
		String authToken = null;

		if(requestHeader != null && requestHeader.startsWith("Bearer ")){
			authToken = requestHeader.substring(7);
			try {
				username = jwtTokenUtils.getUsernameFromToken(authToken);
			}catch (ExpiredJwtException e){

			}
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(jwtTokenUtils.validateToken(authToken, userDetails)){
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		filterChain.doFilter(httpServletRequest,httpServletResponse);
	}
}
