package savings.account.card

import org.springframework.beans.factory.annotation.Autowired
import savings.BaseIntegrationSpec

import static savings.PaybackFixture.creditCardNumber

public class CreditCardRepositoryTest extends BaseIntegrationSpec {

    @Autowired
    CreditCardRepository repository;

    def "shouldFindByNumber"() {
        when:
            CreditCard creditCard = repository.findByNumber(creditCardNumber)
        then:
            creditCard != null
            creditCard.id == 1L
            creditCard.accountId == 1L
            creditCard.number == creditCardNumber;
    }
}
