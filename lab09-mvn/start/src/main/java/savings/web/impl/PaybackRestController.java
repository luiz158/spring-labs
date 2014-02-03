package savings.web.impl;

import org.springframework.beans.factory.annotation.Autowired;

import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.service.PaybackBookKeeper;

// TODO #1 mark this class as a controller component mapped to '/api/payback' url
public class PaybackRestController {

    private final PaybackBookKeeper paybackBookKeeper;

    @Autowired
    public PaybackRestController(PaybackBookKeeper paybackBookKeeper) {
        this.paybackBookKeeper = paybackBookKeeper;
    }

    // TODO #3 make this method respond only to POST request and consume and produce `application/json`
    // TODO #4 make this method return HTTP 201 Created response status
    // TODO #5 make this method serialize the returned value into response body
    // TODO #6 make this method deserialize request body into 'purchase' parameter
    public PaybackConfirmation create(Purchase purchase) {
        return paybackBookKeeper.registerPaybackFor(purchase);
    }
}
