package savings.repository;

import savings.model.PaybackConfirmation;

public interface PaybackRepository {

    PaybackConfirmation save(PaybackConfirmation confirmation);
}
