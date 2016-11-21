package com.example.component;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.example.domain.Model;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class EncyptDataSerializer extends JsonSerializer<Model> {

	@Override
	public Class<Model> handledType() {
		return Model.class;
	}
	
	@Override
	public void serialize(Model value, JsonGenerator json,
			SerializerProvider provider) throws IOException {
//		json.writeObject(value);
		
		
//		while(!json.isClosed()) {
//			System.out.println(json.getCurrentValue());
//		}
		json.writeStartObject();
		json.writeFieldName("name");
		json.writeString(Base64Utils.encodeToString(value.getName().getBytes()));
		json.writeFieldName("type");
		json.writeNumber(value.getType());
		json.writeEndObject();
	}
	
}