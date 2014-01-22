package savings.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackageClasses = ControllerConfiguration.class)
// TODO #1 Enable mvc
public class ControllerConfiguration {

    //TODO #2 make so this method will return a Spring bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        //TODO #3 impement viewresolver with viewClass JSTLView, prefix '/WEB-INF/jsp' and suffix '.jsp'
        return null;
    }
}
