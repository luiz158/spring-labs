package savings.model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;
import static org.joda.time.DateTime.now;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

import org.joda.money.Money;
import org.junit.Test;

import common.math.Percentage;

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
        // TODO #1 implement the case when the policy allows for the payback to be granted
        when(paybackPolicy.isEligible(account, purchase)).thenReturn(TRUE);
        Money payback = merchant.calculatePaybackFor(account, purchase);
        verify(paybackPolicy, times(1)).isEligible(any(Account.class), any(Purchase.class)); //other mock verification
        assertEquals(payback,Money.of(EUR, 6));
    }

    @Test
    public void shouldReturnZeroIfNotEligible() {
        // TODO #2 implement the case when the policy does not allow for the payback to be granted
        when(paybackPolicy.isEligible(account, purchase)).thenReturn(FALSE);
        Money payback = merchant.calculatePaybackFor(account, purchase);
        assertEquals(payback,Money.zero(EUR));
    }
}
