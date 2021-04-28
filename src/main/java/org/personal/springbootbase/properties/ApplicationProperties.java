package org.personal.springbootbase.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "springboot-base")
public class ApplicationProperties {
}
