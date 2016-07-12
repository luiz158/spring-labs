package savings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savings.model.Payback;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
public interface PaybackRepository extends JpaRepository<Payback, Long>, PaybackRepositoryCustom {


}
