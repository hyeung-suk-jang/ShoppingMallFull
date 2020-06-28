package com.example.parayo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.MappedSuperclass;

@Configuration
@MappedSuperclass
@EnableJpaAuditing
public class JpaConfig {
}
