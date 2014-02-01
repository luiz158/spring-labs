package common.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class EmbeddedDatabaseConfiguration {

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public EmbeddedDatabaseFactoryBean dataSource() {
        EmbeddedDatabaseFactoryBean factoryBean = new EmbeddedDatabaseFactoryBean();
        factoryBean.setDatabaseType(EmbeddedDatabaseType.H2);
        factoryBean.setDatabaseName("spring-labs");
        factoryBean.setDatabasePopulator(databasePopulator());
        return factoryBean;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(resourceLoader.getResource("classpath:/META-INF/sql/schema.sql"));
        populator.addScript(resourceLoader.getResource("classpath:/META-INF/sql/data.sql"));
        return populator;
    }

}
