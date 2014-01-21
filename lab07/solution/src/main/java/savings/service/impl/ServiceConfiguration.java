package savings.service.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// TODO #1 enable annotation based transaction management
@Configuration
@ComponentScan(basePackageClasses = ServiceConfiguration.class, excludeFilters = {
        // this filter was added to prevent interference with test configurations
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
})
@EnableTransactionManagement
public class ServiceConfiguration {

    @Autowired
    private DataSource dataSource;

    // TODO #2 define a DataSource based transaction manager bean
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
