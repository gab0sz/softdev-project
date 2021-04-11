package hu.unideb.inf.java.jpa.example.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;


@Entity
public class Car implements Serializable {
    @ManyToOne
    private RegisteredUser owner;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String pic;
    private String name;
    private String registeredBy;
    private String details;
    private String elerhetoseg;

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    private ArrayList<String> tags;





    public Car()
    {

    }

    public Car( String name, String registeredBy,String details,String elerhetoseg) {

        this.elerhetoseg=elerhetoseg;
        this.name = name;
        this.registeredBy = registeredBy;
        this.details=details;
        tags = new ArrayList<>();
        tags.add(name);
        tags.add(registeredBy);
        String[] sp = name.split(" ");
        for (int i = 0; i < sp.length; i++) {
            tags.add(sp[i].toLowerCase());
        }
        String[] split1 = details.split("\n");
        for (int i = 0; i < split1.length; i++) {
            String[] split2 = split1[i].split(": ");
            if(split2.length>1)tags.add(split2[1].toLowerCase());

        }
        String[] split3 = details.split("\n");
        for (int i = 0; i < split3.length; i++) {
            String[] split2 = split1[i].split(": ");
            if(split2.length>1)tags.add(split2[1].toLowerCase());
        }



    }

    @Override
    public String toString() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }
    public String  getName()
    {
        return name;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getElerhetoseg() {
        return elerhetoseg;
    }

    public void setElerhetoseg(String elerhetoseg) {
        this.elerhetoseg = elerhetoseg;
    }
}
