package common.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@PropertySource("classpath:META-INF/application.properties")
public class EmbeddedDatabaseConfiguration {

    @Value("${test.schema.location}")
    private Resource schemaLocation;

    @Value("${test.data.location}")
    private Resource dataLocation;

    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public EmbeddedDatabaseFactoryBean dataSource() {
        EmbeddedDatabaseFactoryBean factoryBean = new EmbeddedDatabaseFactoryBean();
        factoryBean.setDatabaseType(EmbeddedDatabaseType.H2);
        factoryBean.setDatabaseName("spring-labs");
        factoryBean.setDatabasePopulator(databasePopulator());
        return factoryBean;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaLocation);
        populator.addScript(dataLocation);
        return populator;
    }

}
