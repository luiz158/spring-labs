package savings.admin;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.slf4j.Logger.ROOT_LOGGER_NAME;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import savings.account.AccountRepository;

public class RepositoryPerformanceMonitorTest {

    static TestAppender appender = new TestAppender();

    AccountRepository repository = mock(AccountRepository.class);

    @BeforeClass
    public static void setUpClass() throws Exception {
        ((Logger) getLogger(ROOT_LOGGER_NAME)).addAppender(appender);
    }

    @Before
    public void setUp() throws Exception {
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(mock(AccountRepository.class));
        proxyFactory.addAspect(RepositoryPerformanceMonitor.class);
        repository = proxyFactory.getProxy();
    }

    @Test
    public void shouldLogPerformance() throws Exception {
        appender.start();
        repository.findByCreditCardsNumber("123456789");
        appender.stop();

        assertThat(appender.events).hasSize(1);
        assertThat(appender.events.get(0).getLevel()).isEqualTo(Level.INFO);
        assertThat(appender.events.get(0).getMessage())
                .startsWith("StopWatch 'AccountRepository.findByCreditCardsNumber': running time (millis) = ");
    }

    static class TestAppender extends AppenderBase<ILoggingEvent> {

        final List<ILoggingEvent> events = new LinkedList<>();

        @Override
        protected void append(ILoggingEvent event) {
            events.add(event);
        }
    }
}
