package savings.web.impl;

import static org.joda.time.DateTime.now;

import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;

import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.service.PaybackBookKeeper;

// TODO #1 mark this class as a controller component mapped to '/payback' url
public class PaybackController {

    private final PaybackBookKeeper paybackBookKeeper;

    @Autowired
    public PaybackController(PaybackBookKeeper paybackBookKeeper) {
        this.paybackBookKeeper = paybackBookKeeper;
    }

    // TODO #2 enable Joda Money as field type of PurchaseForm by registering appropriate property editor from MoneyModule
    public void replaceMeWithWebDataBinderConfiguration() {}

    /**
     * Notice that by convention this method returns a 'purchaseForm' model attribute
     * and looks up a view by id 'payback/new' to render in response.
     */
    // TODO #3 map this method to '/payback/new' url and make it respond only to GET request
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
    // TODO #4 map this method to the '/payback/confirm' url, make it respond only to POST request
    // and use 'purchaseForm' model attribute populated from the form as method parameter
    public PaybackConfirmation paybackConfirmation(PurchaseForm purchaseForm) {
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
