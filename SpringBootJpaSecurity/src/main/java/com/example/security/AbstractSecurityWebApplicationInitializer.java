package com.example.security;

import org.springframework.web.WebApplicationInitializer;

public abstract class AbstractSecurityWebApplicationInitializer implements
		WebApplicationInitializer {

	private static final String SERVLET_CONTEXT_PREFIX = "org.springframework.web.servlet.FrameworkServlet";
	
	private static final String DEFAULT_FILTER_NAME = "springSecurityFilterChain";
}
