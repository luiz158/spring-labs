
package eu.solidcraft.starter.aop
import base.IntegrationSpec
import eu.solidcraft.starter.infrastructure.security.LoggedUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("aspectTest")
class ExampleAspectIntegrationSpec extends IntegrationSpec {
    @Autowired LoggedUserRepository loggedUserRepository

    def "returned user should have Aspect info attached"() {
        expect:
            loggedUserRepository.getLoggedUserName().endsWith(ExampleAspect.ATTACHED_STRING)
    }
}
