package hu.unideb.inf.java.jpa.example.repository;

import hu.unideb.inf.java.jpa.example.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByName(String name);



}
