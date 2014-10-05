package savings.payback;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaybackRepository extends JpaRepository<Payback, Long>, PaybackRepositoryCustom {

}
