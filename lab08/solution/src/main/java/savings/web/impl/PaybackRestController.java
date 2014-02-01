package savings.web.impl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.service.PaybackBookKeeper;

// TODO #1 mark this class as a controller component mapped to '/api/payback' url
@Controller
@RequestMapping("/api/payback")
public class PaybackRestController {

    private final PaybackBookKeeper paybackBookKeeper;

    @Autowired
    public PaybackRestController(PaybackBookKeeper paybackBookKeeper) {
        this.paybackBookKeeper = paybackBookKeeper;
    }

    // TODO #3 make this method respond only to POST request and consume and produce `application/json`
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    // TODO #4 make this method return HTTP 201 Created response status
    @ResponseStatus(CREATED)
    // TODO #5 make this method serialize the returned value into response body
    @ResponseBody
    // TODO #6 make this method deserialize request body into 'purchase' parameter
    public PaybackConfirmation create(@RequestBody Purchase purchase) {
        return paybackBookKeeper.registerPaybackFor(purchase);
    }
}
