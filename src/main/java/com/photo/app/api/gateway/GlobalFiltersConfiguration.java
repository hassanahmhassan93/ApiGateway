package com.photo.app.api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfiguration {

	final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);

	@Order(1)
	@Bean
	GlobalFilter secondPreFilter() {

		return (exchange, chain) -> {

			logger.info("My second global pre-filter is executed...");

			return chain.filter(exchange).then(Mono.fromRunnable(() -> {

				logger.info("My third global post-filter is executed...");
			}));
		};
	}
	
	@Order(2)
	@Bean
	GlobalFilter thirdPreFilter() {

		return (exchange, chain) -> {

			logger.info("My third global pre-filter is executed...");

			return chain.filter(exchange).then(Mono.fromRunnable(() -> {

				logger.info("My second global post-filter is executed...");
			}));
		};
	}
	
	@Order(3)
	@Bean
	GlobalFilter fourthPreFilter() {

		return (exchange, chain) -> {

			logger.info("My fourth global pre-filter is executed...");

			return chain.filter(exchange).then(Mono.fromRunnable(() -> {

				logger.info("My first global post-filter is executed...");
			}));
		};
	}
}
