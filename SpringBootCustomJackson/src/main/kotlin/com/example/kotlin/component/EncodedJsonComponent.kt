package com.example.kotlin.component

import com.example.kotlin.domain.Model
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import org.springframework.boot.jackson.JsonComponent
import java.io.IOException
import java.util.Base64

@JsonComponent
class EncodedJsonComponent {

    class DecyptDataDeserializer : JsonDeserializer<Model>() {

        override fun handledType(): Class<*> {
            return Model::class.java
        }

        @Throws(IOException::class)
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Model {
            val codec = p.codec
            val node = codec.readTree<JsonNode>(p)

            val it = node.fieldNames()
            while (it.hasNext()) {
                val field = it.next()
                println(field + ":" + node.get(field))
            }

            val name = String(Base64.getDecoder().decode(node.get("name").asText()))
            val type = node.get("type").asInt()
            println("---------------------------------------------------")
            return Model(name, type)
        }
    }

    class EncyptDataSerializer : JsonSerializer<Model>() {

        override fun handledType(): Class<Model> {
            return Model::class.java
        }

        @Throws(IOException::class)
        override fun serialize(value: Model, json: JsonGenerator,
                               provider: SerializerProvider) {
            json.writeStartObject()
            json.writeFieldName("name")
            json.writeString(Base64.getEncoder().encodeToString(value.name.toByteArray()))
            json.writeFieldName("type")
            json.writeNumber(value.type)
            json.writeEndObject()
        }

    }
}
