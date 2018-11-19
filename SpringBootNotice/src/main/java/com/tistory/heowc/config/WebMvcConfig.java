package com.tistory.heowc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/static/"};

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
////		Spring 3.1+
		if (!registry.hasMappingForPattern("/resources/**")) {
			registry.addResourceHandler("/resources/**")
					.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
		
////		Spring 4.1+
//		if (!registry.hasMappingForPattern("/resources/**")) {
//			registry.addResourceHandler("/resources/**")
//					.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
//					.setCachePeriod(3600)
//					.resourceChain(true)
//					.addResolver(new PathResourceResolver());
//		}
//		
////		Spring 4.3.1+ 
//		if (!registry.hasMappingForPattern("/resources/**")) {
//			registry.addResourceHandler("/resources/**")
//					.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
//					.setCachePeriod(3600)
//					.resourceChain(true)
//					.addResolver(new GzipResourceResolver())
//					.addResolver(new PathResourceResolver());
//		}
	}
}
