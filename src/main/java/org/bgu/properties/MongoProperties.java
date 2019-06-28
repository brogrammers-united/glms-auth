package org.bgu.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author William Gentry
 */
@ConfigurationProperties(prefix = "mongo")
public class MongoProperties {

	private String username;
	private char[] password;
	private String databaseName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
}
