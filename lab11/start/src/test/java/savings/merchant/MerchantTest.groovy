package savings.merchant

import common.math.Percentage
import org.joda.money.Money
import spock.lang.Specification

import static org.joda.money.CurrencyUnit.EUR
import static savings.PaybackFixture.*

public class MerchantTest extends Specification {

    def shouldCalculatePaybackIfEligible() {
        given:
            Merchant merchant = new Merchant(merchantNumber, "Guns & Bombs", Percentage.of("6%"), PaybackPolicy.ALWAYS);
        when:
            Money payback = merchant.calculatePaybackFor(account(), purchase());
        then:
            payback == Money.of(EUR, 6);
    }

    def shouldReturnZeroIfNotEligible() {
        given:
            Merchant merchant = new Merchant(merchantNumber, "Guns & Bombs", Percentage.of("6%"), PaybackPolicy.NEVER);
        when:
            Money payback = merchant.calculatePaybackFor(account(), purchase());
        then:
            payback == Money.zero(EUR);
    }
}
