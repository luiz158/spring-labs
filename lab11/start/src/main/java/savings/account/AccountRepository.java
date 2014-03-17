package savings.account;

import org.springframework.data.jpa.repository.JpaRepository;

import savings.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByCreditCardsNumber(String creditCardNumber);
}
