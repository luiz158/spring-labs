package savings.account

import org.springframework.beans.factory.annotation.Autowired
import savings.BaseIntegrationSpec

import static savings.PaybackFixture.creditCardNumber

public class AccountRepositoryTest extends BaseIntegrationSpec {

    @Autowired
    AccountRepository repository;

    def "shouldFindByCreditCardsNumber"() {
        when:
            Account account = repository.findByCreditCardsNumber(creditCardNumber)
        then:
            account != null
            account.id == 1L
    }
}
