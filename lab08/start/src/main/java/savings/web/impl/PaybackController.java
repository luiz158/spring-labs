package savings.web.impl;

import static common.json.MoneyModule.moneyPropertyEditor;
import static org.joda.time.DateTime.now;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.service.PaybackBookKeeper;

//TODO #1 mark this class as a controller component
//TODO #2 map this controller to the '/payback' url
@Controller
@RequestMapping("/payback")
public class PaybackController {

    public static class PurchaseForm {

        public String creditCardNumber;

        public String merchantNumber;

        public Money transactionValue;
    }

    private final PaybackBookKeeper paybackBookKeeper;

    @Autowired
    public PaybackController(PaybackBookKeeper paybackBookKeeper) {
        this.paybackBookKeeper = paybackBookKeeper;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Money.class, moneyPropertyEditor);
    }

    //TODO #2 map this method to the '/new' url and make it respond only to GET request
    @RequestMapping(value = "/new", method = GET)
    public PurchaseForm purchase() {
        return new PurchaseForm();
    }

    //TODO #3 map this method to the '/confirm' url and make it respond only to POST request
    //TODO #4 make purchase a model attribute parameter
    @RequestMapping(value = "/confirm", method = POST)
    public PaybackConfirmation paybackConfirmation(@ModelAttribute PurchaseForm purchaseForm) {
        Purchase purchase = new Purchase(purchaseForm.transactionValue, purchaseForm.creditCardNumber,
                purchaseForm.merchantNumber, now());
        return paybackBookKeeper.registerPaybackFor(purchase);
    }
}
