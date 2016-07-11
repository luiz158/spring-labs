package savings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savings.model.*;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
@Repository
public interface PaybackRepository extends JpaRepository<Payback, Long> {

    List<PaybackConfirmation> findByAccountNumber(String accountNumber);

    PaybackConfirmation save(AccountIncome income, Purchase purchase);

}
