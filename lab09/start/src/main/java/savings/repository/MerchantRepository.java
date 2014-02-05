package savings.repository;

import savings.model.Merchant;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
public interface MerchantRepository {

    Merchant findByNumber(String number);

}
