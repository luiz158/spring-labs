package lab12.security

import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
@TypeChecked
class BackendAuthenticator {
    private AuthenticationManager authenticationManager;

    @Autowired
    BackendAuthenticator(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager
    }

    boolean login(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password))
        boolean isAuthenticated = isAuthenticated(authentication)
        if (isAuthenticated) {
            SecurityContextHolder.getContext().setAuthentication(authentication)
        }
        return isAuthenticated;
    }

    boolean isAuthenticated(Authentication authentication) {
        return authentication != null &&
               !(authentication instanceof AnonymousAuthenticationToken) &&
               authentication.isAuthenticated()
    }
}
