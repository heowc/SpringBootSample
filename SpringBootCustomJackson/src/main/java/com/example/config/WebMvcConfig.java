package com.example.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.component.DecyptDataDeserializer;
import com.example.component.EncyptDataSerializer;
import com.example.domain.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
@EnableWebMvc
@SuppressWarnings("serial")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	DecyptDataDeserializer deserializer;
	
	@Autowired
	EncyptDataSerializer serializer;
	
	@Autowired
	CustomJsonMapper mapper;
	
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter(mapper));
	}

	@Component
	class CustomJsonMapper extends ObjectMapper {
		
		public CustomJsonMapper() {
			SimpleModule module = new SimpleModule();
			module.addSerializer(Model.class, serializer);
			module.addDeserializer(Model.class, deserializer);
			registerModule(module);
		}
	}
}
