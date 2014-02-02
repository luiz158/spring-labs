package eu.solidcraft.starter.domain.workshop;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String email;
    private String role;
    private String name;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }
}
