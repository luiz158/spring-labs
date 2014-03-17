package savings.payback;

import org.springframework.data.jpa.repository.JpaRepository;

import savings.payback.Payback;

public interface PaybackRepository extends JpaRepository<Payback, Long>, PaybackRepositoryCustom {

}
