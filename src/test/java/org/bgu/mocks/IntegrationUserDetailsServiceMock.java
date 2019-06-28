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

/**
 * @author William Gentry
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationUserDetailsServiceMock {

	@MockBean
	protected UserRepository userRepository;

	@MockBean
	protected PasswordEncoder encoder;

	@InjectMocks
	private GlmsUserDetailsService userDetailsService;

	@InjectMocks
	private GlmsAuthenticationProvider authenticationProvider;

	protected final String ENCODED_PASSWORD = "$2a$11$EIgEmib5VnnkckTE7/L4I.Z15BZQDEAw0.CoBMAHNr9xVO2HxoElm";
	protected final User USER = new User("test@email.com", ENCODED_PASSWORD, Arrays.asList(new GlmsAuthority("ROLE_TEST")), true, true, true, true);

	@BeforeEach
	void setUpUserRepository() {
		Mockito.lenient().when(userRepository.findUserByEmail("test@email.com")).thenReturn(USER);
		Mockito.lenient().when(encoder.matches("Password123!", ENCODED_PASSWORD)).thenReturn(true);
	}
}
