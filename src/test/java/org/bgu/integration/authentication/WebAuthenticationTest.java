package org.bgu.integration.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bgu.domain.model.dto.LoginForm;
import org.bgu.mocks.IntegrationUserDetailsServiceMock;
import org.bgu.util.CookieUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author William Gentry
 */
public class WebAuthenticationTest extends IntegrationUserDetailsServiceMock {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@BeforeEach
	void setUpMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	void validLoginForm_ShouldBeOk() throws Exception {
		final LoginForm loginForm = new LoginForm("test@email.com", "Password123!");
		mockMvc.perform(post("/login")
			   .content(mapper.writeValueAsBytes(loginForm))
			   .with(csrf()))
			   .andExpect(status().isOk())
			   .andExpect(cookie().exists(CookieUtils.COOKIE_NAME));
	}

	@Test
	void invalidUsernameLoginForm_ShouldBeUnauthorized() throws Exception {
		final LoginForm loginForm = new LoginForm("invalid@email.com", "Password123!");
		mockMvc.perform(post("/login")
				.content(mapper.writeValueAsBytes(loginForm))
				.with(csrf()))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void invalidPasswordLoginForm_ShouldBeUnauthorized() throws Exception {
		final LoginForm loginForm = new LoginForm("test@email.com", "invalid");
		mockMvc.perform(post("/login")
				.content(mapper.writeValueAsBytes(loginForm))
				.with(csrf()))
				.andExpect(status().isUnauthorized());
	}
}
