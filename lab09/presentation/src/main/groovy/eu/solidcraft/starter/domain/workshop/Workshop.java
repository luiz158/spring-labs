package eu.solidcraft.starter.domain.workshop;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Entity
public class Workshop {
    @Id
    private String name;
    private String teacher;
    private String teachersEmail;
    private String room;
    private Date date;
    private int limit;
    private WorkshopSession workshopSession;
    private Long version;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="workshop_name", referencedColumnName="name")
    private Set<User> students = newHashSet();

    public void setName(String name) {
        this.name = name;
    }

    public void add(User student) {
        students.add(student);
    }
}
