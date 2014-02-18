package savings.repository;

import savings.model.Account;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
public interface AccountRepository {

    Account findByCreditCardsNumber(String creditCardNumber);

}
