package savings.repository;

import savings.model.CreditCard;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
public interface CreditCardRepository {

    CreditCard findByNumber(String number);

}
