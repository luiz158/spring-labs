package lab12

import groovy.transform.TypeChecked
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@TypeChecked
@ComponentScan(basePackages = ["lab12"])
class Application {
    static void main(String[] args) {
        SpringApplication.run(Application, args);
    }
}
