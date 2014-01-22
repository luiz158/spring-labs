package savings.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import savings.controller.ControllerConfiguration;
import savings.repository.impl.RepositoryConfiguration;
import savings.service.impl.ServiceConfiguration;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ControllerConfiguration.class, RepositoryConfiguration.class, ServiceConfiguration.class})
@WebAppConfiguration
public class SpringLabsControllerTest {
    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldGetForm() throws Exception {
        mockMvc.perform(get("/springlabs/purchase/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"));
    }

    @Test
    public void shouldPostForm() throws Exception {
        mockMvc.perform(post("/springlabs/purchase/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"));
    }

    @Test
    public void shouldGetMerchantByNumber() throws Exception {
        mockMvc.perform(get("/springlabs/merchant").param("merchantNumber", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is("1234567890")))
                .andExpect(jsonPath("$.name", is("Guns & Bombs")));
    }

    @Test
    public void shouldGetAccountByCreditCard() throws Exception {
        mockMvc.perform(get("/springlabs/account/1234123412341234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is("123456789")))
                .andExpect(jsonPath("$.name", is("Jane & John Smith")));
    }
}
