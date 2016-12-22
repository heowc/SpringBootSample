package com.tistory.heowc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
//	private static final String CLASSPATH_HTML_LOCATOPNS = "classpath:/WEB-INF/view/**";
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/META-INF/resources/", 
		"classpath:/resources/",
		"classpath:/static/", 
		"classpath:/public/" };
	
	private static final String CLASSPATH_WEBJARS_LOCATOPNS = "classpath:/META-INF/resources/webjars/";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		Spring 3.1+
//		if (!registry.hasMappingForPattern("/**")) {
//			registry.addResourceHandler("/**")
//					.addResourceLocations(CLASSPATH_HTML_LOCATOPNS);
//		}
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**")
					.addResourceLocations(CLASSPATH_WEBJARS_LOCATOPNS);
		}
		if (!registry.hasMappingForPattern("/resources/**")) {
			registry.addResourceHandler("/resources/**")
					.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
		
//		Spring 4.1+ 
//		if (!registry.hasMappingForPattern("/webjars/**")) {
//			registry.addResourceHandler("/webjars/**")
//					.addResourceLocations("classpath:/META-INF/resources/webjars/")
//					.setCachePeriod(3600)
//					.resourceChain(true)
//					.addResolver(new PathResourceResolver());
//		}
//		if (!registry.hasMappingForPattern("/resources/**")) {
//			registry.addResourceHandler("/resources/**")
//					.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
//					.setCachePeriod(3600)
//					.resourceChain(true)
//					.addResolver(new PathResourceResolver());
//		}
		
//		Spring 4.3.1+ 
//		if (!registry.hasMappingForPattern("/webjars/**")) {
//			registry.addResourceHandler("/webjars/**")
//					.addResourceLocations(CLASSPATH_WEBJARS_LOCATOPNS)
//					.setCachePeriod(3600)
//					.resourceChain(true)
//					.addResolver(new GzipResourceResolver())
//					.addResolver(new PathResourceResolver());
//		}
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
