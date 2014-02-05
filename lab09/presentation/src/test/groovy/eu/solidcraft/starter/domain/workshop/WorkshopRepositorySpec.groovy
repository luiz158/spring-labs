package eu.solidcraft.starter.domain.workshop
import base.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class WorkshopRepositorySpec extends IntegrationSpec {
    @Autowired WorkshopRepository workshopRepository
    @Autowired UserRepository userRepository
    String johnDoeEmail = "johnDoe@doe.john"
    User johnDoe
    Workshop johnsWorkshop
    Workshop someOtherWorkshop

    def setup() {
        johnDoe = userRepository.findOne(johnDoeEmail)
        johnsWorkshop = createWorkshopForUser(johnDoe)
        someOtherWorkshop = createSomeOtherWorkshop()
    }

    private Workshop createSomeOtherWorkshop() {
        Workshop someOtherWorkshop = new Workshop(name: "some other workshop")
        return workshopRepository.save(someOtherWorkshop)
    }

    private Workshop createWorkshopForUser(User user) {
        Workshop workshop = new Workshop(name: "the workshop")
        workshop.add(user)
        return workshopRepository.save(workshop)
    }

    def "should find workshops that user is going to"() {
        when:
            List<Workshop> workshops = workshopRepository.findAllByStudentsEmail(johnDoeEmail)
        then:
            workshops.size() == 1
            workshops[0].name == johnsWorkshop.name
    }

    def "should find workshops that user is not registered to"() {
        when:
            List<Workshop> workshops = workshopRepository.findAllByStudentsEmailNot(johnDoeEmail)
        then:
            workshops.size() == 1
            workshops[0].name == someOtherWorkshop.name
    }
}
