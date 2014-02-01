package common.json;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.json.PercentageModule;
import common.math.Percentage;

public class PercentageModuleTest {

    ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new PercentageModule());

    @Test
    public void shouldWriteAndRead() throws Exception {
        TestBean bean = new TestBean("test", Percentage.of("50%"));

        String json = objectMapper.writeValueAsString(bean);
        assertThat(json).isEqualTo("{\"s\":\"test\",\"p\":\"50%\"}");

        TestBean read = objectMapper.readValue(json, TestBean.class);
        assertThat(read.getS()).isEqualTo("test");
        assertThat(read.getP()).isEqualTo(Percentage.of("50%"));
    }

    @Test
    public void shouldWriteAndReadNull() throws Exception {
        TestBean bean = new TestBean("test", null);

        String json = objectMapper.writeValueAsString(bean);
        assertThat(json).isEqualTo("{\"s\":\"test\",\"p\":null}");

        TestBean read = objectMapper.readValue(json, TestBean.class);
        assertThat(read.getS()).isEqualTo("test");
        assertThat(read.getP()).isNull();
    }

    public static class TestBean {

        String s;

        Percentage p;

        public TestBean() {
        }

        public TestBean(String s, Percentage p) {
            this.s = s;
            this.p = p;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public Percentage getP() {
            return p;
        }

        public void setP(Percentage p) {
            this.p = p;
        }
    }
}
