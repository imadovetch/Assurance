package com.Assurance.demo.common.security;

import com.Assurance.demo.AuthDep.domain.Services.UserService;
import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;

@Component
public class BasicLoginHandler implements AuthenticationProvider {

	@Autowired
	private UserService userService;


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		App_User user = userService.findByName(username);

		System.out.println(user);
		if (user == null || !password.equals(user.getPassword())) {
			throw new BadCredentialsException("Invalid username or password");
		}

		// Create a new authenticated user object with authorities (empty in this case)
		List<GrantedAuthority> authorities = Collections.emptyList();
		UserDetails userDetails = User.withUsername(username)
				.password(password)
				.authorities(authorities)
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();

		UsernamePasswordAuthenticationToken authenticatedUser =
				new UsernamePasswordAuthenticationToken(userDetails, password, authorities);

		// Set the authenticated user in the SecurityContextHolder
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

		// Access the HTTP session (optional)
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.setAttribute("user", user); // Store only username (optional)
		}
		System.out.println("Authenticated user: " + authenticatedUser);
		return authenticatedUser;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}