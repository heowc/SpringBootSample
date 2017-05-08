package org.springframework.social.naver.api.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Model {
	public String toJson(boolean prettyPrint) {
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			if (prettyPrint) {
				return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} else {
				return objectMapper.writeValueAsString(this);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
