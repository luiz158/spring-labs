package savings.web;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import savings.model.Account;
import savings.model.Merchant;
import savings.service.PaybackBookKeeper;
import savings.web.impl.WebConfiguration;

//TODO #0 remove @Ignore to run test
// @Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class PaybackControllerTest {

    @Configuration
    @Import(WebConfiguration.class)
    static class Config {

        @Bean
        public PaybackBookKeeper bookKeeper() {
            return Mockito.mock(PaybackBookKeeper.class);
        }
    }

    @Autowired
    WebApplicationContext wac;

    @Autowired
    PaybackBookKeeper bookKeeper;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(this.wac).build();
    }

    //TODO #2 add test that will check if GET request for 'springlabs/purchase/new/' will return 200 (OK)
    // and view named 'form'

    @Test
    public void shouldGetForm() throws Exception {
        mockMvc.perform(get("/payback/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("payback/new"));
    }


    @Test
    public void shouldPostForm() throws Exception {
        mockMvc.perform(post("/payback/confirm"))
                .andExpect(status().isOk())
                .andExpect(view().name("payback/confirm"));
    }

    @Test
    public void shouldGetMerchantByNumber() throws Exception {
        when(bookKeeper.merchantByNumber("1234567890"))
                .thenReturn(new Merchant("1234567890", "Guns & Bombs", null, null));

        mockMvc.perform(get("/payback/merchant").param("merchantNumber", "1234567890"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is("1234567890")))
                .andExpect(jsonPath("$.name", is("Guns & Bombs")));
    }

    @Test
    public void shouldGet404WhenRequestingMerchantByNumber() throws Exception {
        when(bookKeeper.merchantByNumber("36363"))
                .thenThrow(new EmptyResultDataAccessException(1));

        mockMvc.perform(get("/payback/merchant").param("merchantNumber", "36363"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetAccountByCreditCard() throws Exception {
        when(bookKeeper.accountByCreditCard("1234123412341234"))
                .thenReturn(new Account("123456789", "Jane & John Smith"));

        mockMvc.perform(get("/payback/account/1234123412341234"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is("123456789")))
                .andExpect(jsonPath("$.name", is("Jane & John Smith")));
    }

    @Test
    public void shouldGet404WhenRequetsingAccountByCreditCard() throws Exception {
        when(bookKeeper.accountByCreditCard("63363"))
                .thenThrow(new EmptyResultDataAccessException(1));

        mockMvc.perform(get("/payback/account/63363"))
                .andExpect(status().isNotFound());
    }
}
