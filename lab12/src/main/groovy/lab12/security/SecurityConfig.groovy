package lab12.security

import lab12.domain.Role
import lab12.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) {
        http.
            csrf().
                disable().
            requiresChannel().
                and().
            authorizeRequests().
                antMatchers("/resources/**").permitAll().
                antMatchers("/admin").hasAuthority(Role.ADMIN.toString()). //move this line after the next one and see what happens when you log in via test:test
                anyRequest().authenticated().
                and().
            formLogin().
                permitAll().
                defaultSuccessUrl("/index").
                and().
            logout().
                deleteCookies("remember-me").
                invalidateHttpSession(true).
                logoutSuccessUrl("/bye").
                logoutUrl("/logout").
                permitAll().
                and().
            rememberMe().
                key("veryTajnyKeyKtóryNieDajemyNikomu")
    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder)
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder()
    }

    @Bean
    @Override
    UserDetailsService userDetailsService() {
        return userRepository()
    }

    @Bean
    UserRepository userRepository() {
        return new UserRepository(passwordEncoder())
    }


}