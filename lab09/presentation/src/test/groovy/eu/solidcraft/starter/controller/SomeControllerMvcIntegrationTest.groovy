package eu.solidcraft.starter.controller
import base.MvcIntegrationTest
import org.junit.Test
import org.springframework.http.MediaType

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

public class SomeControllerMvcIntegrationTest extends MvcIntegrationTest {

    @Test
    public void shouldRedirectAfterAdd() {
        mockMvc.perform(get('/some/add').
                param('amount', "100").
                accept(MediaType.TEXT_HTML)).
                andExpect(status().isMovedTemporarily()).
                andExpect(redirectedUrl("/some/mine"));
    }

    @Test
    public void shouldAddAndReceive() {
        mockMvc.perform(get('/some/mine').
                accept(MediaType.TEXT_HTML)).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("entities"));
    }

}
