package org.personal.springbootbase.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {

    @Bean
    public FlywayMigrationStrategy repairStrategy(){
        return flyway -> {
            flyway.repair();
            flyway.migrate();
        };
    }
}
