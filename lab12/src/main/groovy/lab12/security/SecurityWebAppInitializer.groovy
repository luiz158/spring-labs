package lab12.security

import groovy.transform.TypeChecked
import org.springframework.core.annotation.Order
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer

@TypeChecked
@Order(2)
class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {
    // Looks unused, right? Don't dare to remove it though. All the work is done in the abstract.
    // It sets up security filter chain
    // (this is a servlet 3 initializer, will be called when war is deployed)
}