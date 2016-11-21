package com.example.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.example.domain.Model;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class DecyptDataDeserializer extends JsonDeserializer<Model> {
	
	@Override
	public Class<?> handledType() {
		return Model.class;
	}
	
	@Override
	public Model deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {
		ObjectCodec codec = p.getCodec();
		JsonNode node = codec.readTree(p);
		
		Iterator<String> it = node.fieldNames();
		while(it.hasNext()) {
			String field = it.next();
			System.out.println(field + ":" + node.get(field));
		}
		
		String name = new String(Base64Utils.decodeFromString(node.get("name").asText()));
		int type = node.get("type").asInt();
		System.out.println("---------------------------------------------------");
		return new Model(name, type);
	}
}