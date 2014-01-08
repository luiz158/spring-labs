package savings.repository.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// TODO #1 enable AspectJ based auto-proxy feature
@Configuration
@ComponentScan(basePackageClasses = RepositoryConfiguration.class)
public class RepositoryConfiguration {

}
