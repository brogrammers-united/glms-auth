package org.bgu.util;

import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.web.server.ServerWebExchange;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Optional;

/**
 * @author William Gentry
 */
public class CookieUtils {

	private CookieUtils() {}

	public static final String COOKIE_NAME = "api_token";
	private static final int EXPIRY = 1000 * 60 * 10; // 10 minutes

	public static void addApiToken(HttpServletResponse response, String value) {
		Cookie cookie = new Cookie(COOKIE_NAME, value);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge(EXPIRY);
		response.addCookie(cookie);
	}

	public static ResponseCookie createCookie(String name, String value) {
		return ResponseCookie.from(name, value).path("/").httpOnly(true).secure(true).sameSite("lax").maxAge(Duration.ofDays(1L)).build();
	}

	public static Optional<String> getJWTFromCookie(ServerWebExchange exchange) {
		HttpCookie cookie = exchange.getRequest().getCookies().getFirst(COOKIE_NAME);
		return cookie == null ? Optional.empty() : Optional.of(cookie.getValue());
	}
}
