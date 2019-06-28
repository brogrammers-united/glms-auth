package org.bgu.config;

import com.mongodb.*;
import org.bgu.properties.MongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author William Gentry
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

	@Autowired
	private MongoProperties mongoProperties;

	@Override
	public MongoClient mongoClient() {
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		builder.connectionsPerHost(25);
		builder.sslEnabled(false);
		builder.minConnectionsPerHost(5);
		builder.alwaysUseMBeans(true);
		builder.maxConnectionIdleTime(30_000);
		builder.readPreference(ReadPreference.primary());
		return new MongoClient(new ServerAddress("127.0.0.1", 27017), MongoCredential.createCredential(mongoProperties.getUsername(), mongoProperties.getDatabaseName(), mongoProperties.getPassword()), builder.build());
	}

	@Override
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
	}

	@Override
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	@Override
	protected String getDatabaseName() {
		return mongoProperties.getDatabaseName();
	}
}
