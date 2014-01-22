package savings.controller;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.format.Formatter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Locale;

public class MoneyFormatter implements Formatter<Money> {

    @Override
    public Money parse(String text, Locale locale) throws ParseException {
        return Money.of(CurrencyUnit.EUR, new BigDecimal(text));
    }

    @Override
    public String print(Money object, Locale locale) {
        return object.getAmount().toString();
    }
}
