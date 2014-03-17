package savings.payback;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import savings.payback.confirm.PaybackConfirmation;
import savings.purchase.Purchase;
import savings.payback.PaybackBookKeeper;

@Controller
@RequestMapping("/api/payback")
public class PaybackResource {

    private final PaybackBookKeeper paybackBookKeeper;

    @Autowired
    public PaybackResource(PaybackBookKeeper paybackBookKeeper) {
        this.paybackBookKeeper = paybackBookKeeper;
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @ResponseBody
    public PaybackConfirmation create(@RequestBody Purchase purchase) {
        return paybackBookKeeper.registerPaybackFor(purchase);
    }
}
