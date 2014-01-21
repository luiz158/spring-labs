package savings.service.impl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.PlatformTransactionManager;

// TODO #1 enable annotation based transaction management
@Configuration
@ComponentScan(basePackageClasses = ServiceConfiguration.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
})
public class ServiceConfiguration {

    // TODO #2 define a DataSource based transaction manager bean
    public PlatformTransactionManager transactionManager() {
        return null;
    }
}
