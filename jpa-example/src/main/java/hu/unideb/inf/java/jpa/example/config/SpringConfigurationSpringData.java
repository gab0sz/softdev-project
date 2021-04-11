package hu.unideb.inf.java.jpa.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "hu.unideb.inf.java.jpa.example.repository")
public class SpringConfigurationSpringData {

}
