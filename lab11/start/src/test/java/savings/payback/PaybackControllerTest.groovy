package savings.payback

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.web.context.WebApplicationContext

import savings.BaseIntegrationSpec
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import static savings.PaybackFixture.creditCardNumber
import static savings.PaybackFixture.merchantNumber

/**
 * This test presents how to test MVC controllers logic utilizing minimized Spring ApplicationContext.
 */
@Ignore // FIXME : when run as separate test works fine, when run together with PaybackBookKeeperTransactionTest (but PaybackControllerTest is executed after)
//					it throws : java.lang.RuntimeException: DB error! (PaybackBookKeeperImpl.java:45).
//				    Seems like behavior recorded in test PaybackBookKeeperTransactionTest -> shouldRegisterPaybackInTransaction is still valid ???
// 					Same happens for PaybackBookKeeperModuleTest.
public class PaybackControllerTest extends BaseIntegrationSpec {

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    def setup() {
        mockMvc = webAppContextSetup(this.wac).build();
    }

    def shouldGetForm() {
        when:
            ResultActions result = mockMvc
                .perform(get("/payback/new"))
                .andDo(print())
        then:
            result
                .andExpect(status().isOk())
                .andExpect(view().name("payback/new"));
    }

    def shouldPostForm() throws Exception {
        when:
            ResultActions result = mockMvc
                .perform(post("/payback/confirm")
                .param("creditCardNumber", creditCardNumber)
                .param("merchantNumber", merchantNumber)
                .param("transactionValue", "100.00 EUR"))
                .andDo(print())
        then:
            result
                .andExpect(status().isOk())
                .andExpect(view().name("payback/confirm"));
    }
}
