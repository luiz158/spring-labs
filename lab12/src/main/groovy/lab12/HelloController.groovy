package lab12
import groovy.transform.TypeChecked
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.GET

@RestController
@TypeChecked
class HelloController {
    @RequestMapping(value = "/", method = GET)
    String main() {
        return ""
    }

    @RequestMapping(value = "/index", method = GET)
    String index() {
        return "index"
    }

    @RequestMapping(value = "/admin", method = GET)
    String admin() {
        return "admin"
    }

    @RequestMapping(value = "/logout", method = GET)
    String logout() {
        return "logout"
    }

    @RequestMapping(value = "/bye", method = GET)
    String bye() {
        return "bye"
    }
}
