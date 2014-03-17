package savings

import com.fasterxml.jackson.databind.ObjectMapper
import common.json.JsonMapperConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.*
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = [
    // this filter was added to prevent interference with test configurations
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
])
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = 'savings')
@Import([JsonMapperConfiguration])
public class PaybackApp {

    public static void main(String[] args) {
        SpringApplication.run(PaybackApp, args);
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Value('${database.data.location}')
    private Resource dataLocation;

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factoryBean = new Jackson2RepositoryPopulatorFactoryBean();
        factoryBean.setMapper(objectMapper);
        factoryBean.setResources([dataLocation] as Resource[]);
        return factoryBean;
    }
}
