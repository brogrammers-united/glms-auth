package org.bgu.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author William Gentry
 */
@Service
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private final GlmsAuthenticationProvider authenticationProvider;
	private final ObjectMapper objectMapper;

	public LoginFilter(GlmsAuthenticationProvider authenticationProvider, ObjectMapper objectMapper, AuthenticationManager manager) {
		super(new AntPathRequestMatcher("/login", HttpMethod.POST.toString()));
		this.authenticationProvider = authenticationProvider;
		this.objectMapper = objectMapper;
		super.setAuthenticationManager(manager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		return authenticationProvider.authenticate(getTokenFromRequest(request));
	}

	// Pulls the login form JSON from HTTP Request
	private UsernamePasswordAuthenticationToken getTokenFromRequest(HttpServletRequest request) throws IOException {
		LoginForm loginForm = objectMapper.readValue(request.getInputStream(), LoginForm.class);
		return new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
	}

	public static class LoginForm {

		private String username;
		private String password;

		public LoginForm() {}

		public LoginForm(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
