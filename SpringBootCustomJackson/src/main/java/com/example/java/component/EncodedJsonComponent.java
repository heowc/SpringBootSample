package com.example.java.component;

import com.example.java.domain.Model;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Iterator;

@JsonComponent
public class EncodedJsonComponent {

    public static class DecyptDataDeserializer extends JsonDeserializer<Model> {

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
            while (it.hasNext()) {
                String field = it.next();
                System.out.println(field + ":" + node.get(field));
            }

            String name = new String(Base64.getDecoder().decode(node.get("name").asText()),
                                     StandardCharsets.UTF_8);
            int type = node.get("type").asInt();
            System.out.println("---------------------------------------------------");
            return new Model(name, type);
        }
    }

    public static class EncyptDataSerializer extends JsonSerializer<Model> {

        @Override
        public Class<Model> handledType() {
            return Model.class;
        }

        @Override
        public void serialize(Model value, JsonGenerator json,
                              SerializerProvider provider) throws IOException {
            json.writeStartObject();
            json.writeFieldName("name");
            json.writeString(Base64.getEncoder().encodeToString(value.getName().getBytes()));
            json.writeFieldName("type");
            json.writeNumber(value.getType());
            json.writeEndObject();
        }

    }
}
