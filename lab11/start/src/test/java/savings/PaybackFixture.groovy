package savings

import common.math.Percentage
import groovy.json.JsonBuilder
import org.joda.money.Money
import org.joda.time.DateTime
import savings.account.Account
import savings.account.objetive.Objective
import savings.merchant.Merchant
import savings.merchant.PaybackPolicy
import savings.payback.confirm.AccountIncome
import savings.payback.confirm.PaybackConfirmation
import savings.purchase.Purchase

import static org.joda.money.CurrencyUnit.EUR

public class PaybackFixture {

    public static final String accountNumber = "123456789";

    public static final String accountName = "Jane & John Smith";

    public static Objective objective1() {
        return new Objective("Glock", Percentage.of("50%"));
    }

    public static Objective objective2() {
        return new Objective("M60", Percentage.of("50%"));
    }

    public static Account account() {
        return new Account(accountNumber, accountName)
                .withObjectives(objective1(), objective2());
    }

    public static final String creditCardNumber = "1234123412341234";

    public static final String merchantNumber = "1234567890";

    public static final String merchantName = "Guns & Bombs";

    public static Merchant merchant() {
        return new Merchant(merchantNumber, merchantName, Percentage.of("6%"), PaybackPolicy.ALWAYS);
    }

    public static Purchase purchase() {
        return purchase(new DateTime(1391257448960L));
    }

    public static Purchase purchase(DateTime date) {
        return new Purchase(Money.of(EUR, 100L), creditCardNumber, merchantNumber, date);
    }

    public static final String purchaseJson = new JsonBuilder([
        amount: "100.00 EUR",
        creditCardNumber: creditCardNumber,
        merchantNumber: merchantNumber,
        date: "2014-02-01T12:24:08.960Z"
    ]).toString()

    public static PaybackConfirmation confirmation(String number) {
        return new PaybackConfirmation(number,
                new AccountIncome(accountNumber, Money.of(EUR, 6),
                        distribution(objective1(), Money.of(EUR, 3L)),
                        distribution(objective2(), Money.of(EUR, 3L))));
    }

    static AccountIncome.Distribution distribution(Objective objective, Money amount) {
        return new AccountIncome.Distribution(objective.credit(amount), amount);
    }

}
