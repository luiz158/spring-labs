package savings.repository;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import savings.model.AccountIncome;
import savings.model.Payback;
import savings.model.PaybackConfirmation;
import savings.model.Purchase;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
@Profile("dev")
public interface PaybackRepository extends JpaRepository<Payback,Long>, PaybackRepositoryCustom{


}
