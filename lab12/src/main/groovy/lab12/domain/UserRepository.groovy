package lab12.domain

import groovy.transform.TypeChecked
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder

//this is so that we don't have to have a JPA dependency
//usually you'd just use Spring Data Repository instead
@TypeChecked
class UserRepository implements UserDetailsService {
    private List<User> users = []

    UserRepository(PasswordEncoder passwordEncoder) {
        users = [
                new User("test", passwordEncoder.encode("test"), [Role.USER]),
                new User("admin", passwordEncoder.encode("admin"), [Role.ADMIN])
        ]
    }

    User findByName(String username) {
        User user = users.find {User it -> it.login == username}
        verifyUserExists(user, username)
        return user
    }

    private void verifyUserExists(User user, String username) {
        if (user == null) {
            throw new UsernameNotFoundException("sorry, no user with name " + username)
        }
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByName(username).createUserDetails()
    }
}
