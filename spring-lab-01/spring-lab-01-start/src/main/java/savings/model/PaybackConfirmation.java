package savings.model;

public class PaybackConfirmation {

    private Long number;

    private AccountIncome income;

    public PaybackConfirmation(AccountIncome income) {
        this(null, income);
    }

    public PaybackConfirmation(Long number, AccountIncome income) {
        this.number = number;
        this.income = income;
    }

    public Long getNumber() {
        return number;
    }

    public AccountIncome getIncome() {
        return income;
    }

    @Override
    public String toString() {
        return "Savings confirmation " + number;
    }
}
