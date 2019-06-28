package org.bgu.unit.authentication;

import org.bgu.domain.model.GlmsAuthenticationToken;
import org.bgu.domain.model.dto.LoginForm;
import org.bgu.mocks.UserDetailsServiceMock;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author William Gentry
 */
public class AuthenticationProviderTest extends UserDetailsServiceMock {

	private final LoginForm VALID_FORM = new LoginForm("test@email.com", "Password123!");
	private final LoginForm INVALID_USERNAME_FORM = new LoginForm("invalid@email.com", "Password123!");
	private final LoginForm INVALID_PASSWORD_FORM = new LoginForm("test@email.com", "invalid");

	@Test
	void validCredentials_ShouldProduceAuthentication() {
		final Authentication authentication = authenticationProvider.authenticate(new GlmsAuthenticationToken(VALID_FORM));
		assertThat(authentication).isNotNull();
		assertThat(authentication.getName()).isNotBlank();
		assertThat(authentication.getAuthorities()).isNotEmpty();
		assertThat(authentication.isAuthenticated()).isTrue();
	}

	@Test
	void invalidPassword_ShouldThrow_BadCredentialsException() {
		assertThrows(BadCredentialsException.class, () -> authenticationProvider.authenticate(new GlmsAuthenticationToken(INVALID_PASSWORD_FORM)));
	}

	@Test
	void invalidUsername_ShouldThrow_BadCredentialsException() {
		assertThrows(BadCredentialsException.class, () -> authenticationProvider.authenticate(new GlmsAuthenticationToken(INVALID_USERNAME_FORM)));
	}
}
