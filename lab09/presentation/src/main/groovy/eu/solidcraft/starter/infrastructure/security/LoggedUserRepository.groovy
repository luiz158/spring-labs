package eu.solidcraft.starter.infrastructure.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

@Component
class LoggedUserRepository {
    String getLoggedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
        if(!isAuthenticated(authentication)) {
            throw new SessionAuthenticationException("No user found in session")
        }
        return authentication.getName()
    }

    private static boolean isAuthenticated(Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()
    }
}
