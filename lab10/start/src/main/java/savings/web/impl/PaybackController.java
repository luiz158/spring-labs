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

import org.springframework.web.servlet.ModelAndView;
import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.service.PaybackBookKeeper;

@Controller
@RequestMapping("/payback")
//TODO #3 handle expection RuntimeException, returning status code 418 (HttpStatus.I_AM_A_TEAPOT)
public class PaybackController {

    private final PaybackBookKeeper paybackBookKeeper;

    @Autowired
    public PaybackController(PaybackBookKeeper paybackBookKeeper) {
        this.paybackBookKeeper = paybackBookKeeper;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Money.class, moneyPropertyEditor);
    }

    @RequestMapping(method = GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("payback/list");
        modelAndView.addObject("paybacks", paybackBookKeeper.getAllPaybacks());
        return modelAndView;
    }

    /**
     * Notice that by convention this method returns a 'purchaseForm' model attribute
     * and looks up a view by id 'payback/new' to render in response.
     */
    @RequestMapping(value = "/new", method = GET)
    public PurchaseForm purchase() {
        PurchaseForm purchaseForm = new PurchaseForm();
        purchaseForm.setCreditCardNumber("1234123412341234");
        purchaseForm.setMerchantNumber("1234567890");
        return purchaseForm;
    }

    /**
     * Analogous to the above, this method returns a 'paybackConfirmation' model attribute
     * and looks up a view by id 'payback/confirm' to render in response.
     */
    // TODO #1 add validation for 'purchaseForm' and bindingResults
    // TODO #2 return ModelAndView with appropriate view, depending on validation results
    @RequestMapping(value = "/confirm", method = POST)
    public PaybackConfirmation paybackConfirmation(@ModelAttribute PurchaseForm purchaseForm) {
        return paybackBookKeeper.registerPaybackFor(new Purchase(
                purchaseForm.transactionValue,
                purchaseForm.creditCardNumber,
                purchaseForm.merchantNumber,
                now()));
    }

    public static class PurchaseForm {

        public String creditCardNumber;

        public String merchantNumber;

        public Money transactionValue;

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

        public Money getTransactionValue() {
            return transactionValue;
        }

        public void setTransactionValue(Money transactionValue) {
            this.transactionValue = transactionValue;
        }
    }
}
