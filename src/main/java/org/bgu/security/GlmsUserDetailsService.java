package org.bgu.security;

import org.bgu.domain.model.User;
import org.bgu.domain.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author William Gentry
 */
@Service
public class GlmsUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public GlmsUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userRepository.findUserByEmail(username);
		if (user == null)
			throw new BadCredentialsException("Invalid Credentials");
		return user;
	}
}
