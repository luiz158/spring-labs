package savings.purchase;

import static common.json.JsonMapperConfiguration.buildObjectMapper;
import static org.fest.assertions.Assertions.assertThat;
import static savings.PaybackFixture.purchase;
import static savings.PaybackFixture.purchaseJson;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import spock.lang.Specification;

public class PurchaseMappingTest extends Specification {

    ObjectMapper objectMapper = buildObjectMapper();

    def shouldName() throws Exception {
        given:
            Purchase purchase = purchase();
        when:
            String json = objectMapper.writeValueAsString(purchase);
            Purchase read = objectMapper.readValue(json, Purchase.class);
        then:
            json == purchaseJson;
            read.amount == purchase.amount
            read.creditCardNumber == purchase.creditCardNumber
            read.merchantNumber == purchase.merchantNumber
            read.date.millis == purchase.date.millis
    }
}
