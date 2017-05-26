package com.example.config;

import com.example.component.DecyptDataDeserializer;
import com.example.component.EncyptDataSerializer;
import com.example.domain.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	private final DecyptDataDeserializer deserializer;
	private final EncyptDataSerializer serializer;

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter(getObjectMapper()));
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(Model.class, serializer);
		module.addDeserializer(Model.class, deserializer);
		return new ObjectMapper().registerModule(module);
	}
}
