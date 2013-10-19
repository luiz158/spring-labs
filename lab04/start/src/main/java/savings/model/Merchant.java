package savings.model;

import static java.math.RoundingMode.HALF_EVEN;

import org.joda.money.Money;

import common.math.Percentage;
import common.model.Entity;

public class Merchant extends Entity {

    private String number;

    private String name;

    private Percentage payback;

    // TODO #1 add a constructor argument of type PaybackPolicy - use 'change signature' refactoring and 'null' as default value
    // TODO #2 assign the new argument to a new private field called 'paybackPolicy' - use IDE hints (Ctrl+1 in Eclipse)
    public Merchant(String number, String name, Percentage payback) {
        this.number = number;
        this.name = name;
        this.payback = payback;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Percentage getPayback() {
        return payback;
    }

    public Money calculatePaybackFor(Account account, Purchase purchase) {
        // TODO #3 ask the policy if the payback is available; if yes, then return calculated amount; otherwise return zero
        return purchase.getAmount().multipliedBy(payback.asBigDecimal(), HALF_EVEN);
    }

    @Override
    public String toString() {
        return "Merchant number = '" + number + "', name = '" + name + "', payback = " + payback;
    }
}
