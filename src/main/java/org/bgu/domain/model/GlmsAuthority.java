package org.bgu.domain.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author William Gentry
 */
public class GlmsAuthority implements GrantedAuthority {

	private final String authority;

	public GlmsAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
}
