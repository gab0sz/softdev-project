package hu.unideb.inf.java.jpa.example.repository;

import hu.unideb.inf.java.jpa.example.domain.RegisteredUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, Long> {
    RegisteredUser findByName(String name);
    RegisteredUser findByUsername(String username );




}
