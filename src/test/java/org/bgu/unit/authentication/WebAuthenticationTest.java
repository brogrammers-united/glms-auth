package org.bgu.unit.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bgu.mocks.UserDetailsServiceMock;
import org.bgu.security.LoginFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author William Gentry
 */
public class WebAuthenticationTest extends UserDetailsServiceMock {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;

	MockMvc mockMvc;

	@BeforeEach
	void setUpMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	void validLoginForm_ShouldBeOk() throws Exception {
		mockMvc.perform(post("/login")
				.with(csrf())
				.content(mapper.writeValueAsBytes(new LoginFilter.LoginForm("test@email.com", "Password123!"))))
				.andExpect(status().isOk());
	}
}
