package savings.repository;

import savings.model.AccountIncome;
import savings.model.PaybackConfirmation;
import savings.model.Purchase;

import java.util.List;

/**
 * Created by bartosz on 12.07.16.
 */
public interface PaybackRepositoryCustom {

    List<PaybackConfirmation> findByAccountNumber(String accountNumber);

    PaybackConfirmation save(AccountIncome income, Purchase purchase);
}
