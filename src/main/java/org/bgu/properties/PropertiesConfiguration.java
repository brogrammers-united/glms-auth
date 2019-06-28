package org.bgu.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author William Gentry
 */
@Configuration
@EnableConfigurationProperties({MongoProperties.class})
public class PropertiesConfiguration {
}
