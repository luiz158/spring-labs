package savings.payback

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification

import static common.json.JsonMapperConfiguration.buildObjectMapper
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static savings.PaybackFixture.confirmation
import static savings.PaybackFixture.purchaseJson

/**
 * Notice that you do _not_ need a Spring ApplicationContext to test MVC controllers using MockMvc.
 */
public class PaybackResourceTest extends Specification {

    PaybackBookKeeper bookKeeper = Mock(PaybackBookKeeper.class);

    PaybackResource controller = new PaybackResource(bookKeeper);

    MockMvc mockMvc = standaloneSetup(controller)
        .setMessageConverters(buildJsonMessageConverter(buildObjectMapper()))
        .build();

    def "shouldCreatePayback"() {
        given:
            bookKeeper.registerPaybackFor(_) >> { return confirmation("123") }
        when:
            ResultActions result = mockMvc
                .perform(post("/api/payback")
                .content(purchaseJson).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
                .andDo(print())
        then:
            result
                .andExpect(status().isCreated())
                .andExpect(jsonPath('$.number').value("123"))
                .andExpect(jsonPath('$.income.amount').value("6.00 EUR"))
                .andExpect(jsonPath('$.income.distributions').isArray())
                .andExpect(jsonPath('$.income.distributions[0].amount').value("3.00 EUR"))
                .andExpect(jsonPath('$.income.distributions[1].amount').value("3.00 EUR"));
    }

    private static HttpMessageConverter<?> buildJsonMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
