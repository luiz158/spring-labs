package savings.web.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import common.json.MoneyModule;
import common.json.PercentageModule;

@Configuration
@ComponentScan(basePackageClasses = WebConfiguration.class, excludeFilters = {
        // this filter was added to prevent interference with test configurations
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
})
// TODO #1 Enable Web MVC support with appropriate annotation
// TODO #2 Enable fine tuning of Web MVC configuration by extending from convenient configurer adapter
public class WebConfiguration {

    /*
     * This part of configuration is for classic MVC.
     */

    // TODO #3 override one of base class methods to gain access to ResourceHandlerRegistry and configure serving
    // 'src/main/webapp/resources' by Spring resources handler;
    // i.e. 'src/main/webapp/resources/css/main.css' should be available under '/resources/css/main.css';
    public void replaceMeWithResourceHandlersConfig() {}

    // TODO #4 Configure a view resolver to serve internal '.jsp' resources stored in '/WEB-INF/jsp/'
    @Bean
    public ViewResolver defaultViewResolver() {
        return null;
    }

    /*
     * The rest of the configuration is for REST-ful MVC.
     */

    // TODO #5 override one of base class methods to configure JSON message converter to automatically convert
    // JSON request body into entities and entities into JSON response body;
    public void replaceMeWithMessageConvertersConfig() {}

    public static HttpMessageConverter<?> buildJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(buildObjectMapper());
        return converter;
    }

    /**
     * We're using Jackson2 to handle JSON-Object mapping.
     */
    public static ObjectMapper buildObjectMapper() {
        return new ObjectMapper()
                .registerModules(
                        new PercentageModule(),
                        new MoneyModule(),
                        new JodaModule())
                .disable(
                        SerializationFeature.FAIL_ON_EMPTY_BEANS,
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
