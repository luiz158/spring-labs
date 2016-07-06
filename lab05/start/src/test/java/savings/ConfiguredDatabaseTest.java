package savings;

import common.sql.TestDataSourceFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import savings.repository.impl.RepositoryConfiguration;
import savings.service.impl.ServiceConfiguration;

import javax.sql.DataSource;

/**
 * Created by piotr on 06.07.16.
 */
@ContextConfiguration
public abstract class ConfiguredDatabaseTest extends AbstractJUnit4SpringContextTests {

    @Configuration
    @Import({RepositoryConfiguration.class, ServiceConfiguration.class})
    @PropertySource("classpath:META-INF/application.properties")
    static class Config{
        @Value("${test.schema.location}")
        private Resource schemaLocation;

        @Value("${test.data.location}")
        private Resource dataLocation;

        @Bean
        public FactoryBean<DataSource> dataSource() {
            return new TestDataSourceFactory(schemaLocation, dataLocation);
        }
    }
}
