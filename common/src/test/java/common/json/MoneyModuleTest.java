package common.json;

import static org.fest.assertions.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.math.Percentage;

public class MoneyModuleTest {

    ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new MoneyModule());

    @Test
    public void shouldWriteAndRead() throws Exception {
        TestBean bean = new TestBean("test", Money.of(EUR, 50L));

        String json = objectMapper.writeValueAsString(bean);
        assertThat(json).isEqualTo("{\"s\":\"test\",\"m\":\"50.00 EUR\"}");

        TestBean read = objectMapper.readValue(json, TestBean.class);
        assertThat(read.getS()).isEqualTo("test");
        assertThat(read.getM()).isEqualTo(Money.of(EUR, 50L));
    }

    @Test
    public void shouldWriteAndReadNull() throws Exception {
        TestBean bean = new TestBean("test", null);

        String json = objectMapper.writeValueAsString(bean);
        assertThat(json).isEqualTo("{\"s\":\"test\",\"m\":null}");

        TestBean read = objectMapper.readValue(json, TestBean.class);
        assertThat(read.getS()).isEqualTo("test");
        assertThat(read.getM()).isNull();
    }

    public static class TestBean {

        String s;

        Money m;

        public TestBean() {
        }

        public TestBean(String s, Money m) {
            this.s = s;
            this.m = m;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public Money getM() {
            return m;
        }

        public void setM(Money m) {
            this.m = m;
        }
    }
}
