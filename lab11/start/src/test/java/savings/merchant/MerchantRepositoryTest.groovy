package savings.merchant

import common.math.Percentage
import org.springframework.beans.factory.annotation.Autowired
import savings.BaseIntegrationSpec

import static savings.PaybackFixture.merchantNumber

public class MerchantRepositoryTest extends BaseIntegrationSpec {

    @Autowired
    MerchantRepository repository;

    def "shouldFindByNumber"() {
        when:
            Merchant merchant = repository.findByNumber(merchantNumber);
        then:
            merchant != null
            merchant.id == 1L
            merchant.name == "Guns & Bombs"
            merchant.payback == Percentage.of("6%")
            merchant.paybackPolicy == PaybackPolicy.ALWAYS
    }
}
