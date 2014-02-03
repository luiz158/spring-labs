package savings.model.workshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkshopRepository extends JpaRepository<Workshop, String> {
    //Find workshops that user is going to
    List<Workshop> findAllByStudentsEmail(String email);

    //Find workshops that user is not registered to
    @Query(value = "select * from Workshop where name not in (select distinct workshop_name from user where email = ?1)", nativeQuery = true)
    List<Workshop> findAllByStudentsEmailNot(String email);
}
