package org.dbWandy.config;

import org.dbWandy.util.EasySqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public EasySqlInjector sqlInjector() {
        return new EasySqlInjector();
    }
}
