package savings.account.card;

import org.springframework.data.jpa.repository.JpaRepository;

import savings.account.card.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    CreditCard findByNumber(String number);
}
