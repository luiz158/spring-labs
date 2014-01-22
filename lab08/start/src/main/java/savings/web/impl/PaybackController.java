package savings.web.impl;

import static org.joda.money.CurrencyUnit.EUR;
import static org.joda.time.DateTime.now;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import savings.model.Account;
import savings.model.Merchant;
import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.repository.AccountRepository;
import savings.repository.MerchantRepository;
import savings.service.PaybackBookKeeper;

//TODO #1 mark this class as a controller component
//TODO #2 map this controller to the '/payback' url
@Controller
@RequestMapping("/payback")
public class PaybackController {

    public static class PurchaseForm {

        private String creditCardNumber;

        private String merchantNumber;

        public String getCreditCardNumber() {
            return creditCardNumber;
        }

        public void setCreditCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
        }

        public String getMerchantNumber() {
            return merchantNumber;
        }

        public void setMerchantNumber(String merchantNumber) {
            this.merchantNumber = merchantNumber;
        }
    }

    private final PaybackBookKeeper paybackBookKeeper;

    @Autowired
    public PaybackController(PaybackBookKeeper paybackBookKeeper) {
        this.paybackBookKeeper = paybackBookKeeper;
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
        Purchase purchase = new Purchase(Money.of(EUR, 100), purchaseForm.creditCardNumber,
                purchaseForm.merchantNumber, now());
        return paybackBookKeeper.registerPaybackFor(purchase);
    }

    //TODO #5 map this method to the '/merchant' and make it respond only to GET request
    //TODO #6 make this method return JSON object
    //TODO #7 match 'merchantNumber' parameter to request parameter 'merchantNumber'
    @RequestMapping(value = "/merchant", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Merchant merchantByNumber(@RequestParam String merchantNumber) {
        return paybackBookKeeper.merchantByNumber(merchantNumber);
    }

    //TODO #8 map this method to the '/account/{creditCard}' and make it respond only to GET request
    //TODO #9 make this method return JSON object
    //TODO #10 match 'creditCard' parameter to param variable 'creditCard'
    @RequestMapping(value = "/account/{creditCard}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Account accountByCreditCard(@PathVariable String creditCard) {
        return paybackBookKeeper.accountByCreditCard(creditCard);
    }
}
