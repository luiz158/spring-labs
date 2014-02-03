package savings.model.workshop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class WorkshopRepositoryTest extends IntegrationTest {
    @Autowired WorkshopRepository workshopRepository;
    @Autowired UserRepository userRepository;
    String johnDoeEmail = "johnDoe@doe.john";
    User johnDoe;
    Workshop johnsWorkshop;
    Workshop someOtherWorkshop;

    @Before
    public void setup() {
        johnDoe = userRepository.findOne(johnDoeEmail);
        johnsWorkshop = createWorkshopForUser(johnDoe);
        someOtherWorkshop = createSomeOtherWorkshop();
    }

    private Workshop createSomeOtherWorkshop() {
        Workshop someOtherWorkshop = new Workshop();
        someOtherWorkshop.setName("some other workshop");
        return workshopRepository.save(someOtherWorkshop);
    }

    private Workshop createWorkshopForUser(User user) {
        Workshop workshop = new Workshop();
        workshop.setName("the workshop");
        workshop.add(user);
        return workshopRepository.save(workshop);
    }

    @Test
    public void shouldFindWorkshopsThatUserIsGoingTo() {
        //when
        List<Workshop> workshops = workshopRepository.findAllByStudentsEmail(johnDoeEmail);
        //then
        assertEquals(workshops.size(), 1);
        assertEquals(workshops.get(0).getName(), johnsWorkshop.getName());
    }

    @Test
    public void shouldFindWorkshopsThatUserIsNotRegisteredTo() {
        //when
        List<Workshop> workshops = workshopRepository.findAllByStudentsEmailNot(johnDoeEmail);
        //then
        assertEquals(workshops.size(), 1);
        assertEquals(workshops.get(0).getName(), someOtherWorkshop.getName());
    }
}
