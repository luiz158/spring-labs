package savings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savings.model.Account;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByCreditCardsNumber(String creditCardNumber);

}
