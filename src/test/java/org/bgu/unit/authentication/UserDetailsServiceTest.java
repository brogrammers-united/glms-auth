package org.bgu.unit.authentication;

import org.bgu.mocks.UserDetailsServiceMock;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author William Gentry
 */
public class UserDetailsServiceTest extends UserDetailsServiceMock {

	@Test
	public void validUserID_ShouldProducePopulatedUser() {
		final UserDetails userDetails = userDetailsService.loadUserByUsername("test@email.com");
		assertThat(userDetails).isNotNull();
		assertThat(userDetails.getUsername()).isNotBlank();
		assertThat(userDetails.getPassword()).isNotBlank();
		assertThat(userDetails.getAuthorities()).isNotEmpty();
		assertThat(userDetails.isEnabled()).isTrue();
		assertThat(userDetails.isAccountNonExpired()).isTrue();
		assertThat(userDetails.isCredentialsNonExpired()).isTrue();
		assertThat(userDetails.isAccountNonLocked()).isTrue();
	}

	@Test
	public void invalidUserID_ShouldProduce_BadCredentialsException() {
		assertThrows(BadCredentialsException.class, () -> userDetailsService.loadUserByUsername("invalid@email.com"));
	}
}
