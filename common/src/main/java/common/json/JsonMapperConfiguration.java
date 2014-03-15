package common.json;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
public class JsonMapperConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return buildObjectMapper();
    }

    public static ObjectMapper buildObjectMapper() {
        return new ObjectMapper()
                .registerModules(
                        new PercentageModule(),
                        new MoneyModule(),
                        new JodaModule())
                .disable(
                        SerializationFeature.FAIL_ON_EMPTY_BEANS,
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
                );
    }
}
