package org.bgu.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author William Gentry
 */
@Document(collection = "glms_user")
public class User implements UserDetails {

	@Id
	private ObjectId id;

	@Indexed(unique = true)
	private String email;

	private String password;

	private List<GlmsAuthority> authorities;

	private boolean enabled;

	private boolean accountNonLocked;

	private boolean accountNonExpired;

	private boolean credentialsNonExpired;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUsername() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@PersistenceConstructor
	public User(String email, String password, List<GlmsAuthority> authorities, boolean enabled, boolean accountNonLocked, boolean accountNonExpired, boolean credentialsNonExpired) {
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.accountNonLocked = accountNonLocked;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public List<GlmsAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<GlmsAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
}
