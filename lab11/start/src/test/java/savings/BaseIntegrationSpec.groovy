package savings

import common.spring.TypeSafeBeanPostProcessor
import org.mockito.AdditionalAnswers
import org.mockito.Mockito
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import savings.payback.PaybackRepository
import spock.lang.Specification

import static org.mockito.AdditionalAnswers.delegatesTo
import static org.mockito.Mockito.mock

@WebAppConfiguration
@ContextConfiguration(classes = PaybackApp, loader = SpringApplicationContextLoader, initializers = TestInitializer)
public abstract class BaseIntegrationSpec extends Specification {

    @Order
    public static class TestInitializer extends TypeSafeBeanPostProcessor<PaybackRepository>
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        void initialize(ConfigurableApplicationContext applicationContext) {
            ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory()
            beanFactory.addBeanPostProcessor(this)
        }

        @Override
        PaybackRepository safelyPostProcessAfterInitialization(PaybackRepository bean, String beanName) throws BeansException {
            return mock(PaybackRepository, delegatesTo(bean))
        }
    }
}
