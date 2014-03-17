package savings.merchant;

import org.springframework.data.jpa.repository.JpaRepository;

import savings.merchant.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    Merchant findByNumber(String number);
}
