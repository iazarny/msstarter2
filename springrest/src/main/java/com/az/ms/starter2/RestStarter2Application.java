package com.az.ms.starter2;

import com.az.model.TodoEntiry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@SpringBootApplication(
		scanBasePackages = {"com.az"},
		exclude = SecurityAutoConfiguration.class)
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan( basePackageClasses = com.az.model.TodoEntiry.class)
public class RestStarter2Application {

	public static void main(String[] args) {
		SpringApplication.run(RestStarter2Application.class, args);
	}

}
