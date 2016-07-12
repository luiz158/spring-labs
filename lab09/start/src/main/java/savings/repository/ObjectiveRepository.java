package savings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savings.model.Objective;

// TODO #1: implement this repository to support basic CRUD operations as well as those defined below
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
}
