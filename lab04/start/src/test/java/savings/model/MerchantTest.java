package savings.model;

import static org.joda.money.CurrencyUnit.EUR;
import static org.joda.time.DateTime.now;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;

import common.math.Percentage;
import org.mockito.Mockito;

public class MerchantTest {

    Account account = new Account("123456789", "Jane & John Smith")
            .withObjective("Glock", Percentage.of("50%"))
            .withObjective("M60", Percentage.of("50%"));

    String merchantNumber = "1234567890";

    Purchase purchase = new Purchase(Money.of(EUR, 100L), "1234123412341234", merchantNumber, now());

    PaybackPolicy paybackPolicy = mock(PaybackPolicy.class);

    Merchant merchant = new Merchant(merchantNumber, "Guns & Bombs", Percentage.of("6%"), paybackPolicy);

    // TODO #0 a good practice when implementing tests is to start with an assertion to get the red bar first

    @Test
    public void shouldCalculatePaybackIfEligible() {
        Mockito.when(paybackPolicy.isEligible(account, purchase)).thenReturn(true);
        Assert.assertEquals(Money.of(EUR,6),
                merchant.calculatePaybackFor(account,purchase));
    }

    @Test
    public void shouldReturnZeroIfNotEligible() {
        Mockito.when(paybackPolicy.isEligible(account, purchase)).thenReturn(false);
        Assert.assertEquals(Money.zero(EUR),
                merchant.calculatePaybackFor(account,purchase));
    }
}
