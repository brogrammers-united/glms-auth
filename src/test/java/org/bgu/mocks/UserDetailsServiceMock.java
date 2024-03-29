package org.bgu.mocks;

import org.bgu.domain.model.GlmsAuthority;
import org.bgu.domain.model.User;
import org.bgu.domain.repository.UserRepositoryImpl;
import org.bgu.security.GlmsAuthenticationProvider;
import org.bgu.security.GlmsUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.mock;

/**
 * @author William Gentry
 */
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceMock {

	@Mock
	protected UserRepositoryImpl userRepository;

	protected GlmsUserDetailsService userDetailsService;

	protected GlmsAuthenticationProvider authenticationProvider;

	protected final String ENCODED_PASSWORD = "$2a$11$EIgEmib5VnnkckTE7/L4I.Z15BZQDEAw0.CoBMAHNr9xVO2HxoElm";
	protected final User USER = new User("test@email.com", ENCODED_PASSWORD, Arrays.asList(new GlmsAuthority("ROLE_TEST")), true, true, true, true);

	@BeforeEach
	void setUpUserRepository() {
		Mockito.lenient().when(userRepository.findUserByEmail("test@email.com")).thenReturn(USER);
		userDetailsService = new GlmsUserDetailsService(userRepository);

		PasswordEncoder encoder = mock(PasswordEncoder.class);
		Mockito.lenient().when(encoder.matches("Password123!", ENCODED_PASSWORD)).thenReturn(true);
		authenticationProvider = new GlmsAuthenticationProvider(userDetailsService, encoder);
	}
}
