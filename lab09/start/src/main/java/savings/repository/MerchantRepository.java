package savings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savings.model.Merchant;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
public interface MerchantRepository extends JpaRepository<Merchant,Long> {

    Merchant findByNumber(String number);

}
