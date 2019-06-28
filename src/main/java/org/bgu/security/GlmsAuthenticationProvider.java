package org.bgu.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author William Gentry
 */
@Service
public class GlmsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private final GlmsUserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	public GlmsAuthenticationProvider(GlmsUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// Validates provided password matches persisted password
		if (!passwordEncoder.matches((CharSequence) authentication.getCredentials(), userDetails.getPassword()))
			throw new BadCredentialsException("Invalid Credentials");

		// Any other validations here
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
		if (userDetails == null)
			throw new BadCredentialsException("Invalid Credentials");
		return userDetails;
	}
}
