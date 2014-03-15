package common.json;

import static com.fasterxml.jackson.core.io.NumberInput.parseBigDecimal;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.util.StringUtils;

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

public class MoneyModule extends SimpleModule {

    public static String getAsString(Money money) {
        if (money == null) {
            return null;
        }
        return money.getAmount().toString() + " " + money.getCurrencyUnit().toString();
    }

    public static Money getAsMoney(String string) {
        if (!StringUtils.hasText(string)) {
            return null;
        }
        String[] split = string.split(" ");
        return Money.of(CurrencyUnit.of(split[1]), parseBigDecimal(split[0]));
    }

    public MoneyModule() {
        addSerializer(Money.class, moneySerializer);
        addDeserializer(Money.class, moneyDeserializer);
    }

    public static final StdScalarSerializer<Money> moneySerializer = new StdScalarSerializer<Money>(Money.class) {

        @Override
        public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException, JsonGenerationException {
            jsonGenerator.writeString(getAsString(money));
        }
    };

    public static final StdScalarDeserializer<Money> moneyDeserializer = new StdScalarDeserializer<Money>(Money.class) {

        @Override
        public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException, JsonProcessingException {

            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
                return getAsMoney(jsonParser.getValueAsString());
            }

            throw deserializationContext.mappingException("Expected money string!");
        }
    };

    public static final PropertyEditor moneyPropertyEditor = new PropertyEditorSupport() {

        @Override
        public String getAsText() {
            return getAsString((Money) getValue());
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(getAsMoney(text));
        }
    };
}
