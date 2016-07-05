package savings;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;
import static org.joda.time.DateTime.now;

import org.joda.money.Money;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.service.PaybackBookKeeper;

// TODO #1 make this test extend from AbstractJUnit4SpringContextTests
// TODO #2 configure ApplicationContext for this test using @ContextConfiguration and its locations attribute
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring/application-context.xml",
        "classpath:/META-INF/spring/datasource-testcontext.xml"
})
//@RunWith(SpringJUnit4ClassRunner) + delete bookKeeper + @Autowired->bookKeeper
public class PaybackBookKeeperModuleTest extends AbstractJUnit4SpringContextTests {

    PaybackBookKeeper bookKeeper = null;

    String creditCardNumber = "1234123412341234";

    String merchantNumber = "1234567890";

    Purchase purchase = new Purchase(Money.of(EUR, 100L), creditCardNumber, merchantNumber, now());

    // TODO #3 remove this field and use the one from super class that was configured for you
    //ConfigurableApplicationContext applicationContext;

    @Before
    public void setUp() {
        // TODO #4.1 remove manual configuration of ApplicationContext - the base class handles that now
//        applicationContext = new ClassPathXmlApplicationContext(
//                "classpath:/META-INF/spring/application-context.xml",
//                "classpath:/META-INF/spring/datasource-testcontext.xml"
//        );
        bookKeeper = applicationContext.getBean("paybackBookKeeper", PaybackBookKeeper.class);
    }

//    @After
//    public void tearDown() {
//        // TODO #4.2 remove manual configuration of ApplicationContext - the base class handles that now
//        //applicationContext.close();
//    }

    @Test
    public void shouldThrowWhenAccountNotFound() {
        purchase = new Purchase(Money.of(EUR, 100L), "4321432143214321", merchantNumber, now());

        catchException(bookKeeper, EmptyResultDataAccessException.class).registerPaybackFor(purchase);

        assertThat(caughtException()).isNotNull();
    }

    @Test
    public void shouldThrowWhenMerchantNotFound() {
        purchase = new Purchase(Money.of(EUR, 100L), creditCardNumber, "1111111111", now());

        catchException(bookKeeper, EmptyResultDataAccessException.class).registerPaybackFor(purchase);

        assertThat(caughtException()).isNotNull();
    }

    @Test
    public void shouldRegisterPayback() {
        PaybackConfirmation confirmation = bookKeeper.registerPaybackFor(purchase);

        assertThat(confirmation.getNumber()).isNotNull();
        assertThat(confirmation.getIncome().getAmount()).isEqualTo(Money.of(EUR, 6L));
        assertThat(confirmation.getIncome().getDistributions()).hasSize(2);
        assertThat(confirmation.getIncome().getDistribution("Glock").getAmount()).isEqualTo(Money.of(EUR, 3L));
        assertThat(confirmation.getIncome().getDistribution("M60").getAmount()).isEqualTo(Money.of(EUR, 3L));
    }
}
