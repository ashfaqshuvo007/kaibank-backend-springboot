package com.kaibank.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The JpaConfig class creates a bean of type AuditorAware and enables JPA auditing
 *
 * @author Pabasara
 * @version 1.0
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
  @Bean
  public AuditorAware<String> auditorAware() {
    return new AuditorAwareImpl();
  }
}
