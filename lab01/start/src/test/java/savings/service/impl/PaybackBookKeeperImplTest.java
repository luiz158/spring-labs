package savings.service.impl;

import static com.googlecode.catchexception.CatchException.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;
import static org.joda.time.DateTime.now;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.joda.money.Money;
import org.junit.Test;

import common.math.Percentage;
import savings.model.Account;
import savings.model.Merchant;
import savings.model.PaybackConfirmation;
import savings.repository.AccountRepository;
import savings.repository.MerchantRepository;
import savings.repository.NotFoundException;
import savings.repository.PaybackRepository;
import savings.service.Purchase;

public class PaybackBookKeeperImplTest {

    AccountRepository accountRepository = mock(AccountRepository.class);

    MerchantRepository merchantRepository = mock(MerchantRepository.class);

    PaybackRepository paybackRepository = mock(PaybackRepository.class);

    // PaybackBookKeeperImpl bookKeeper =

    String creditCardNumber = "1234";

    String merchantNumber = "4321";

    Purchase purchase = new Purchase(Money.of(EUR, 100L), creditCardNumber, merchantNumber, now());

    Account account = new Account("5555", "Jane & John Smith")
            .withObjective("Glock", Percentage.of("50%"))
            .withObjective("M60", Percentage.of("50%"));

    Merchant merchant = new Merchant(merchantNumber, "Guns'n'Bombs", Percentage.of("6%"));

    @Test
    public void shouldThrowWhenAccountNotFound() {
        when(accountRepository.findByCreditCard(creditCardNumber)).thenThrow(new NotFoundException());

        //catchException(bookKeeper, NotFoundException.class).registerPaybackFor(...);

        assertThat(caughtException()).isNotNull();
    }

    @Test
    public void shouldThrowWhenMerchantNotFound() {
        when(merchantRepository.findByNumber(merchantNumber)).thenThrow(new NotFoundException());

        //catchException(bookKeeper, NotFoundException.class).registerPaybackFor(...);

        assertThat(caughtException()).isNotNull();
    }

    @Test
    public void shouldRegisterPayback() {
        when(accountRepository.findByCreditCard(creditCardNumber)).thenReturn(account);
        when(merchantRepository.findByNumber(merchantNumber)).thenReturn(merchant);
        doAnswer(returnsFirstArg()).when(paybackRepository).save(any(PaybackConfirmation.class));

        PaybackConfirmation confirmation = null; // replace with actual call to bookKeeper.registerPaybackFor(..)

        assertThat(confirmation.getIncome().getAmount()).isEqualTo(Money.of(EUR, 6L));
        assertThat(confirmation.getIncome().getDistributions()).hasSize(2);
        assertThat(confirmation.getIncome().getDistribution("Glock").getAmount()).isEqualTo(Money.of(EUR, 3L));
        assertThat(confirmation.getIncome().getDistribution("M60").getAmount()).isEqualTo(Money.of(EUR, 3L));
    }
}
