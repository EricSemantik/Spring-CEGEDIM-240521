package spring.formation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JPAConfiguration.class, WebConfig.class })
public class AppConfig {

}
