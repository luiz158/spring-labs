package base

import eu.solidcraft.starter.conf.security.HttpBasicSecurityConfig
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.User
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = ["classpath:/spring/test.ioc.xml"])
@ActiveProfiles(profiles = ['starter.test'])
abstract class MvcIntegrationTest {
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private AuthenticationManager authenticationManager
    protected User user
    protected MockMvc mockMvc;

    @Before
    void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Before
    void loginUser() {
        user = new User(HttpBasicSecurityConfig.USERNAME, HttpBasicSecurityConfig.PASSWORD, [])
        BackendAuthenticator authenticator = new BackendAuthenticator(authenticationManager)
        authenticator.login(user.username, user.password)
    }
}
