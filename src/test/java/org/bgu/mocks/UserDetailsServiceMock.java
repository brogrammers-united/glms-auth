package org.bgu.mocks;

import org.bgu.domain.model.GlmsAuthority;
import org.bgu.domain.model.User;
import org.bgu.domain.repository.UserRepository;
import org.bgu.security.GlmsAuthenticationProvider;
import org.bgu.security.GlmsUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author William Gentry
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserDetailsServiceMock {

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	protected GlmsUserDetailsService userDetailsService;

	@InjectMocks
	protected GlmsAuthenticationProvider authenticationProvider;

	protected final User USER = new User("test@email.com", "Password123!", Arrays.asList(new GlmsAuthority("ROLE_TEST")), true, true, true, true);

	@BeforeEach
	void setUpUserRepository() {
		Mockito.lenient().when(userRepository.findUserByEmail("test@email.com")).thenReturn(USER);
	}

	@BeforeEach
	void setUpPasswordEncoder() {
		Mockito.lenient().when(passwordEncoder.matches("Password123!", any())).thenReturn(true);
	}
}
