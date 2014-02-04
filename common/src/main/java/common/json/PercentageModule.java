package common.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import common.math.Percentage;

public class PercentageModule extends SimpleModule {

    public PercentageModule() {
        addSerializer(Percentage.class, new PercentageSerializer());
        addDeserializer(Percentage.class, new PercentageDeserializer());
    }

    public static class PercentageSerializer extends StdScalarSerializer<Percentage> {

        public PercentageSerializer() {
            super(Percentage.class);
        }

        @Override
        public void serialize(Percentage percentage, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException, JsonGenerationException {
            jsonGenerator.writeString(percentage.toString());
        }
    }

    public static class PercentageDeserializer extends StdScalarDeserializer<Percentage> {

        public PercentageDeserializer() {
            super(Percentage.class);
        }

        @Override
        public Percentage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException, JsonProcessingException {

            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
                return Percentage.of(jsonParser.getValueAsString());
            }

            throw deserializationContext.mappingException("Expected percentage string!");
        }

    }
}
