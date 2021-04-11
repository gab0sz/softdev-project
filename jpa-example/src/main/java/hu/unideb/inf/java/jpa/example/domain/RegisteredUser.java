package hu.unideb.inf.java.jpa.example.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class RegisteredUser {
    @OneToMany
    private Collection<Car> cars;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String username,password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RegisteredUser() {
    }

    //@PreUpdate
    // @PrePersist
    //public void updateLastUpdate() {
    //    setLastUpdate(new Date());
    //}

    @Override
    public String toString() {
        return name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
