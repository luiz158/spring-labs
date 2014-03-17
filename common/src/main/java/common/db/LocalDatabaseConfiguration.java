package common.db;

import javax.sql.DataSource;

import org.h2.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.json.JsonMapperConfiguration;

@Configuration
@Import(JsonMapperConfiguration.class)
public class LocalDatabaseConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${database.url:jdbc:h2:tcp://localhost/~/spring-labs/test}")
    private String databaseUrl;

    @Value("${test.data.location:classpath:/META-INF/sql/data.json}")
    private Resource dataLocation;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factoryBean = new Jackson2RepositoryPopulatorFactoryBean();
        factoryBean.setMapper(objectMapper);
        factoryBean.setResources(new Resource[] { dataLocation });
        return factoryBean;
    }

}
