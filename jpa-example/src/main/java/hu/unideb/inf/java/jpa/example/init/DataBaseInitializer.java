package hu.unideb.inf.java.jpa.example.init;

import hu.unideb.inf.java.jpa.example.domain.Car;
import hu.unideb.inf.java.jpa.example.domain.RegisteredUser;
import hu.unideb.inf.java.jpa.example.repository.CarRepository;
import hu.unideb.inf.java.jpa.example.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


@Service
public class DataBaseInitializer {
    private CarRepository CarRepo;
    private RegisteredUserRepository UserRepo;


    @Autowired
    public DataBaseInitializer(CarRepository CarRepo,RegisteredUserRepository UserRepo) {
        this.CarRepo = CarRepo;
        this.UserRepo = UserRepo;
    }
    
    public void init() {
  // String name, String registeredBy,String details,String elerhetoseg

        try {
            Scanner read = new Scanner(new File("src/database.txt"));

            while (read.hasNextLine())
            {

                String[] split = read.nextLine().split(",");
                String elerhetoseg=read.nextLine()+"\n";
                elerhetoseg+=read.nextLine()+"\n";
                String details="";
                for (int i = 0; i < 7; i++) {
                    details+=read.nextLine()+"\n";
                }
                Car tmp = new Car(split[0],split[1],details,elerhetoseg);
                tmp.setPic(read.nextLine());
                CarRepo.save(tmp);
            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            Scanner read = new Scanner(new File("src/users.txt"));

            while (read.hasNextLine())
            {

                String[] split = read.nextLine().split(",");
                RegisteredUser n = new RegisteredUser();
                n.setId(Integer.parseInt(split[0]));
                n.setName(split[1]);
                n.setUsername(split[2]);
                n.setPassword(split[3]);
                UserRepo.save(n);
            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*  RegisteredUser b = new RegisteredUser();
            b.setName("Tisza Zoltán");
            b.setUsername("zoli");
            b.setPassword("Dragonxxx123");
            UserRepo.save(b);*/


        }
}
