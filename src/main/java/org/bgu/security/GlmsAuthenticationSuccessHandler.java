package org.bgu.security;

import org.bgu.util.CookieUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author William Gentry
 */
public class GlmsAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// TODO: Add cookie to request with Auth Token
		response.setStatus(HttpServletResponse.SC_OK);
		CookieUtils.addApiToken(response, "change-me");
	}
}
