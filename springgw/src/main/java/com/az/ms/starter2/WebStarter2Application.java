package com.az.ms.starter2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@SpringBootApplication(
		scanBasePackages = {"com.az"},
		exclude = SecurityAutoConfiguration.class)
@EnableConfigurationProperties()
@RestController
public class WebStarter2Application {

	public static void main(String[] args) {
		SpringApplication.run(WebStarter2Application.class, args);
	}


	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		String httpUri = "http://rest-st-2:22222";
		return builder.routes()

				.route(p -> p
						.path("/api")
						.uri(httpUri))
				.route(p -> p
						.path("/api/todos")
						.uri(httpUri))
				.build();
	}

	@RequestMapping("/")
	public Mono<String> fallback() {
		return Mono. just("<head><meta http-equiv=\"refresh\" content=\"0; URL=/index.html\" /></head>");
	}


}
