package base

import eu.solidcraft.starter.conf.security.HttpBasicSecurityConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.User
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = ["classpath:/spring/test.ioc.xml"])
//WARNING: cannot use Profiles class here, thought this has to equal to Profiles.TEST, because: http://jira.codehaus.org/browse/GROOVY-3278
@ActiveProfiles(profiles = ['starter.test'])
@WebAppConfiguration
abstract class IntegrationSpec extends Specification {
    @Autowired private AuthenticationManager authenticationManager
    private BackendAuthenticator authenticator
    protected User user

    def setup() {
        authenticator = new BackendAuthenticator(authenticationManager)
        user = new User(HttpBasicSecurityConfig.USERNAME, HttpBasicSecurityConfig.PASSWORD, [])
        login(user)
    }

    def cleanup() {
        logout()
    }

    void login(User user) {
        authenticator.login(user.username, user.password)
    }

    void logout() {
        authenticator.logout()
    }
}
