package org.bgu.domain.model;

import org.bgu.domain.model.dto.LoginForm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author William Gentry
 */
public class GlmsAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private final boolean authenticated;

	public GlmsAuthenticationToken(LoginForm loginForm) {
		super(loginForm.getUsername(), loginForm.getPassword());
		this.authenticated = false;
	}

	public GlmsAuthenticationToken(UserDetails userDetails) {
		super(userDetails.getUsername(), "", userDetails.getAuthorities());
		this.authenticated = true;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}
}
