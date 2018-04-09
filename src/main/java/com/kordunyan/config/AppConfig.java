package com.kordunyan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	private static final String PROP_EXTERNAL_IMAGES = "external.images";

	private final Environment env;

	@Autowired
	public AppConfig(Environment env) {
		this.env = env;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/images/**")
				.addResourceLocations(String.format("file:///%s", env.getRequiredProperty(PROP_EXTERNAL_IMAGES)));
	}
}
