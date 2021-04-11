package hu.unideb.inf.java.jpa.example.domain;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DisplayClass {


    public String getName() {
        return name;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public ImageView getPicture() {
        return picture;
    }

    String name;
       String registeredBy;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    String details;

    public String getElerhetoseg() {
        return elerhetoseg;
    }

    String elerhetoseg;

    public void setElerhetoseg(String elerhetoseg) {
        this.elerhetoseg = elerhetoseg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    ImageView picture;

    public DisplayClass(String name, String registeredBy, String src,String details,String elerhetoseg) throws FileNotFoundException {
        this.name = name;
        this.elerhetoseg=elerhetoseg;
        this.registeredBy = registeredBy;
        this.details=details;


        FileInputStream input = new FileInputStream(src);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        this.picture = imageView;
    }
}
