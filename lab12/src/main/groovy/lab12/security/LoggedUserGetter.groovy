package lab12.security

import groovy.transform.TypeChecked
import lab12.domain.User
import lab12.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@TypeChecked
@Service
class LoggedUserGetter {
    private UserRepository userRepository
    private BackendAuthenticator backendAuthenticator

    @Autowired
    LoggedUserGetter(UserRepository userRepository, BackendAuthenticator backendAuthenticator) {
        this.userRepository = userRepository
        this.backendAuthenticator = backendAuthenticator
    }

    //lightweight, no touching the database
    public UserDetails getLoggedUserDetails() {
        UserDetails loggedUserDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (backendAuthenticator.isAuthenticated(authentication)) {
            Object principal = authentication.getPrincipal()
            if (principal instanceof UserDetails) {
                loggedUserDetails = ((UserDetails) principal)
            } else {
                throw new RuntimeException("Expected class of authentication principal is AuthenticationUserDetails. Given: "
                        + principal.getClass())
            }
        }
        return loggedUserDetails
    }

    //lightweight, no touching the database
    public String getLoggedUserName() {
        return getLoggedUserDetails().getUsername()
    }

    //heavyweight, touching the database, don't use if you can use one of those above
    public User getLoggedUser() {
        return userRepository.findByName(getLoggedUserName())
    }
}
