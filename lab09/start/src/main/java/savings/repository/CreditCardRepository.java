package savings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savings.model.CreditCard;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    CreditCard findByNumber(String number);

}
