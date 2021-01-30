package com.rbs.interview.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class ApplicationCacheConfig {

	@Bean("customKeyGenerator")
	public KeyGenerator keyGenerator() {
		return new CustomKeyGenerator();
	}

}
