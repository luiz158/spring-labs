package lab12.domain
import groovy.transform.TypeChecked
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@TypeChecked
class User {
    private String login
    private String passwordHash
    private List<Role> roles

    User(String login, String passwordHash, List<Role> roles) {
        this.login = login
        this.passwordHash = passwordHash
        this.roles = roles
    }

    UserDetails createUserDetails() {
        List<SimpleGrantedAuthority> grantedAuthorities = roles.collect{Role it -> new SimpleGrantedAuthority(it.toString())}
        return new org.springframework.security.core.userdetails.User(login, passwordHash, true, true, true, true,
                grantedAuthorities)
    }

    String getLogin() {
        return login
    }
}
