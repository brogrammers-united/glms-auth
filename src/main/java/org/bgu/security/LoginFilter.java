package org.bgu.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bgu.domain.model.dto.LoginForm;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
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
	private final AuthenticationSuccessHandler successHandler = new GlmsAuthenticationSuccessHandler();

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

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		successHandler.onAuthenticationSuccess(request, response, authResult);
	}

	// Pulls the login form JSON from HTTP Request
	private UsernamePasswordAuthenticationToken getTokenFromRequest(HttpServletRequest request) throws IOException {
		LoginForm loginForm = objectMapper.readValue(request.getInputStream(), LoginForm.class);
		return new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
	}
}
