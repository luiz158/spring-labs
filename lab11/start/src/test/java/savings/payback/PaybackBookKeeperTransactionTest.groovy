package savings.payback

import org.joda.money.Money
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionCallback
import org.springframework.transaction.support.TransactionTemplate
import savings.BaseIntegrationSpec
import savings.account.AccountRepository
import savings.account.objetive.Objective
import savings.payback.confirm.AccountIncome
import savings.purchase.Purchase

import static org.mockito.Matchers.any
import static org.mockito.Mockito.doThrow
import static savings.PaybackFixture.*

/**
 * This is just to prove the point - transactions _do_ work in Spring.
 * So, _don't_ test it in your app!
 */
public class PaybackBookKeeperTransactionTest extends BaseIntegrationSpec {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PaybackRepository paybackRepository;

    @Autowired
    PaybackBookKeeper bookKeeper;

    @Autowired
    PlatformTransactionManager transactionManager;

    def "shouldRegisterPaybackInTransaction"() {
        given:
            doThrow(new RuntimeException("DB error!"))
                .when(paybackRepository).save(any(AccountIncome.class), any(Purchase.class));
            int countBefore = paybackRepository.findByAccountNumber(accountNumber).size()
            Map<String, Money> savingsBefore = getSavings()
        when:
            bookKeeper.registerPaybackFor(purchase());
        then:
            thrown(RuntimeException.class)
            countBefore == paybackRepository.findByAccountNumber(accountNumber).size()
            savingsBefore == getSavings()
    }

    private Map<String, Money> getSavings() {
        return new TransactionTemplate(transactionManager).execute({
            accountRepository.findByCreditCardsNumber(creditCardNumber).objectives.collectEntries {
                Objective objective -> [objective.name, objective.savings]
            }
        } as TransactionCallback<Map<String, Money>>)
    }
}
