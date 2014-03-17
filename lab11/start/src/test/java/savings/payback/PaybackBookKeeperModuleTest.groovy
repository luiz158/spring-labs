package savings.payback

import org.joda.money.Money
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import savings.BaseIntegrationSpec
import savings.payback.confirm.PaybackConfirmation
import savings.purchase.Purchase

import static com.google.common.collect.Iterables.size
import static org.joda.money.CurrencyUnit.EUR
import static org.joda.time.DateTime.now
import static savings.PaybackFixture.*

public class PaybackBookKeeperModuleTest extends BaseIntegrationSpec {

    @Autowired
    PaybackBookKeeper bookKeeper;

    def "shouldThrowWhenAccountNotFound"() {
        given:
            Purchase purchase = new Purchase(Money.of(EUR, 100L), "4321432143214321", merchantNumber, now())
        when:
            bookKeeper.registerPaybackFor(purchase)
        then:
            thrown(EmptyResultDataAccessException.class)
    }

    def "shouldThrowWhenMerchantNotFound"() {
        given:
            Purchase purchase = new Purchase(Money.of(EUR, 100L), creditCardNumber, "1111111111", now())
        when:
            bookKeeper.registerPaybackFor(purchase)
        then:
            thrown(EmptyResultDataAccessException.class)
    }

    def "shouldRegisterPayback"() {
        when:
            PaybackConfirmation confirmation = bookKeeper.registerPaybackFor(purchase())
        then:
            confirmation.number != null
            confirmation.income.amount == Money.of(EUR, 6L)
            size(confirmation.income.distributions) == 2
            confirmation.income.getDistribution("Glock").amount == Money.of(EUR, 3L)
            confirmation.income.getDistribution("M60").amount == Money.of(EUR, 3L)
    }
}
