package pcy.study.api.config.jpa

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = ["pcy.study.db"])
@EnableJpaRepositories(basePackages = ["pcy.study.db"])
class JpaConfig
