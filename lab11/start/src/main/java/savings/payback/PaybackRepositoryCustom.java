package savings.payback;

import java.util.List;

import savings.payback.confirm.AccountIncome;
import savings.payback.confirm.PaybackConfirmation;
import savings.purchase.Purchase;

public interface PaybackRepositoryCustom {

    List<PaybackConfirmation> findByAccountNumber(String accountNumber);

    PaybackConfirmation save(AccountIncome income, Purchase purchase);
}
