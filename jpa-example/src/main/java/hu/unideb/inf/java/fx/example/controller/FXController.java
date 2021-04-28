package hu.unideb.inf.java.fx.example.controller;

import hu.unideb.inf.java.jpa.example.domain.Car;
import hu.unideb.inf.java.jpa.example.domain.DisplayClass;
import hu.unideb.inf.java.jpa.example.domain.RegisteredUser;
import hu.unideb.inf.java.jpa.example.repository.CarRepository;
import hu.unideb.inf.java.jpa.example.repository.RegisteredUserRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class FXController implements Initializable {
    @Autowired
    private CarRepository CarRepo;
    private int top=0;
    @Autowired
    private RegisteredUserRepository UserRepo;
    @FXML
    Label cantlogin,regerror;
    @FXML
    Pane outerlayer,searchlayer,loginlayer,registerlayer,addlayer,myCarslayer,resetpwLayer;
    @FXML
    Button choose1btn,choose2btn,loginbtn,registerbtn,browse,tomycars,backtoaddlayerbtn,backtoaddlayerpwbtn,resetpwbtn;
    @FXML
    AnchorPane anchor;
    @FXML
    TextField searchinput;
    @FXML Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,loglab1,loglab2,loglab3,reglab1,reglab2,reglab3;

    @FXML
    TextField username,password,regFullname,regPassword,regUsername;
    @FXML
    TableView<DisplayClass> table = new TableView<>();
    @FXML
    TableColumn<DisplayClass,String> col1 = new TableColumn();
    @FXML
    TableColumn<DisplayClass,String> col2= new TableColumn();
    @FXML
    TableColumn<DisplayClass,String> col4= new TableColumn();
    @FXML
    TableColumn<DisplayClass,String> col5= new TableColumn();
    @FXML
    TableColumn<DisplayClass, ImageView> col3 = new TableColumn();
    private  String currentlogin="";
    private  String pathToPic="";
    @FXML Button submitcar,backfromadding,backbtn1,regbtn,regback,backsearch;

    @FXML TextField evjaratTEXT;
    @FXML TextField hengerurtartalomTEXT;
    @FXML TextField muszakiTEXT;
    @FXML TextField kilometerTEXT;
    @FXML TextField ajtokszamaTEXT;
    @FXML TextField szinTEXT;
    @FXML TextField tomegTEXT;
    @FXML TextField emailTEXT;
    @FXML TextField telefonTEXT;
    @FXML TextField carInputName;
    @FXML Label errorAtInput;




    public ObservableList list = FXCollections.observableArrayList();







    public void searchSequence()
    {
        list.clear();
        table.setItems(list);
         ArrayList<Car> l = new ArrayList<>();
        l.addAll((Collection<? extends Car>) CarRepo.findAll());
        ArrayList<DisplayClass> d = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            String[] split = searchinput.getText().split(" ");
            boolean contains=false;
            for (int j = 0; j < split.length; j++) {
               if(l.get(i).getTags().contains(split[j].toLowerCase()))contains=true;
            }

           if(contains || searchinput.getText()=="")
           {
               DisplayClass tmp = null;
               try {
                   tmp = new DisplayClass(l.get(i).getName(),l.get(i).getRegisteredBy(),l.get(i).getPic(),l.get(i).getDetails(),
                           l.get(i).getElerhetoseg());
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               }
               d.add(tmp);
           }


        }

        System.out.println(d.size());
        list.addAll(d);
        table.setItems(list);


    }
    public void hengerRestrict(){

        if(hengerurtartalomTEXT.getText().length()!=0)
        {
            if(!Character.isDigit(hengerurtartalomTEXT.getText().charAt(hengerurtartalomTEXT.getText().length()-1)))
            {
                hengerurtartalomTEXT.setText("");
            }
        }



    }

    public void muszakiRestrict(){

        if(muszakiTEXT.getText().length()!=0)
        {
            if(!Character.isDigit(muszakiTEXT.getText().charAt(muszakiTEXT.getText().length()-1)) )
            {

                if(muszakiTEXT.getText().charAt(muszakiTEXT.getText().length()-1)!='.' )
                {
                    muszakiTEXT.setText("");
                }


            }


        }



    }

    public void kilometerRestrict(){

        if(kilometerTEXT.getText().length()!=0)
        {
            if(!Character.isDigit(kilometerTEXT.getText().charAt(kilometerTEXT.getText().length()-1)))
            {
                kilometerTEXT.setText("");
            }
        }



    }

    public void ajtokszamaRestrict(){

        if(ajtokszamaTEXT.getText().length()!=0)
        {
            if(!Character.isDigit(ajtokszamaTEXT.getText().charAt(ajtokszamaTEXT.getText().length()-1)))
            {
                ajtokszamaTEXT.setText("");
            }
        }




    }
    public void evjaratRestrict()
    {
        if(evjaratTEXT.getText().length()!=0)
        {
            if(!Character.isDigit(evjaratTEXT.getText().charAt(evjaratTEXT.getText().length()-1)))
            {
                evjaratTEXT.setText("");
            }

        }

    }
    public void tomegRestrict()
    {
        if(tomegTEXT.getText().length()!=0)
        {
            if(!Character.isDigit(tomegTEXT.getText().charAt(tomegTEXT.getText().length()-1)))
            {
                tomegTEXT.setText("");
            }
        }




    }

    private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

   /*  @FXML TextField evjaratTEXT;
    @FXML TextField hengerurtartalomTEXT;
    @FXML TextField muszakiTEXT;
    @FXML TextField kilometerTEXT;
    @FXML TextField ajtokszamaTEXT;
    @FXML TextField szinTEXT;
    @FXML TextField tomegTEXT;
    @FXML TextField emailTEXT;
    @FXML TextField telefonTEXT;*/
    public void submitCar() throws IOException {
        if((pathToPic=="" || pathToPic ==null)|| carInputName.getText()==""||
           hengerurtartalomTEXT.getText()=="" || evjaratTEXT.getText()=="" ||
           kilometerTEXT.getText()=="" || ajtokszamaTEXT.getText()=="" ||
                szinTEXT.getText()=="" || tomegTEXT.getText()=="" ||
           emailTEXT.getText()=="" || telefonTEXT.getText()=="")
         {
            errorAtInput.setText("Minden mezőt töltsön ki, és adjon meg egy képfájlt!");
         }

         else
         {
             String extension="";
             int d =0;
             for (int i =pathToPic.length()-1 ;pathToPic.charAt(i)!='.'  ; i--) {
                  d=i;
             }

             for (int i = d; i <pathToPic.length() ; i++) {
                 extension=extension+pathToPic.charAt(i);
             }
             if(!extension.equals("jpg") && !extension.equals("png"))
             {
                 errorAtInput.setText("Csak png és jpg fáljkiterjesztés a megengedett!");
             }
             else
             {
                 String details ="Évjárat: "+evjaratTEXT.getText()+"\n";
                 details +="Hengerűrtartalom: "+hengerurtartalomTEXT.getText()+" cm3"+"\n";

                 details +="Műszaki vizsga érvényesség: "+muszakiTEXT.getText()+"\n";

                 details +="Kilométer óra állása: "+kilometerTEXT.getText()+" km"+"\n";

                 details +="Ajtók száma: "+ajtokszamaTEXT.getText()+"\n";

                 details +="Szín: "+szinTEXT.getText()+"\n";
                 details +="Tömeg: "+tomegTEXT.getText()+" kg"+"\n";

                 String elerhetoseg="";

                 elerhetoseg +="Telefon: "+telefonTEXT.getText()+"\n";
                 elerhetoseg +="E-mail: "+emailTEXT.getText()+"\n";

                 errorAtInput.setText("Sikeres hozzáadás!");
                 String[] sp=pathToPic.split(Pattern.quote(File.separator));



                 Car tmp = new Car(carInputName.getText(),currentlogin,details,elerhetoseg);
                 File src = new File(pathToPic);
                 File dst= new File("src/img/"+top+"pic.jpg");
                 copyFileUsingJava7Files(src,dst);
                 tmp.setPic("src/img/"+top+"pic.jpg");

                 CarRepo.save(tmp);
                 carInputName.setText("");
                 pathToPic="";
                 telefonTEXT.setText("");
                 emailTEXT.setText("");
                 evjaratTEXT.setText("");
                 hengerurtartalomTEXT.setText("");
                 muszakiTEXT.setText("");
                 kilometerTEXT.setText("");
                 ajtokszamaTEXT.setText("");
                 szinTEXT.setText("");
                 tomegTEXT.setText("");

                 try {
                     FileWriter writer = new FileWriter("src/database.txt", true);
                     writer.write(tmp.getName()+","+tmp.getRegisteredBy()+"\n");
                     writer.write(tmp.getElerhetoseg());
                     writer.write(tmp.getDetails());
                     writer.write("src/img/"+top+"pic.jpg\n");
                     writer.close();

                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 top++;








             }



         }
    }

    public void backFromAdding()
    {
        addlayer.setVisible(false);
        outerlayer.setVisible(true);

    }


    public void browsefiles()
    {

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            pathToPic=selectedFile.getAbsolutePath();
        }
        else {

        }





    }





































    @FXML
    public void loginSequence()
    {
        pathToPic="";
        RegisteredUser person = UserRepo.findByUsername(username.getText());
        if(person!=null)
        {
             if(person.getPassword().equals(password.getText()))
             {
                 loginlayer.setVisible(false);
                 addlayer.setVisible(true);
                 currentlogin=person.getUsername();

             }
             else
             {
                 cantlogin.setText("Hibás felhasználónév/jelszó");
                 username.setText("");
                 password.setText("");
             }
        }
        else
        {
            cantlogin.setText("Hibás felhasználónév/jelszó");
            username.setText("");
            password.setText("");
        }


    }
    public void backFromSearch()
    {
        searchlayer.setVisible(false);
        outerlayer.setVisible(true);
    }
    public void backFromRegister()
    {
        registerlayer.setVisible(false);
        loginlayer.setVisible(true);
    }
     public void backFromLogin()
     {
         loginlayer.setVisible(false);
         outerlayer.setVisible(true);

     }
    @FXML
    public void choice_guest()
    {
        outerlayer.setVisible(false);
        searchlayer.setVisible(true);


        list.clear();
        table.setItems(list);
        ArrayList<Car> l = new ArrayList<>();
        l.addAll((Collection<? extends Car>) CarRepo.findAll());
        ArrayList<DisplayClass> d = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {



                DisplayClass tmp = null;
                try {
                    tmp = new DisplayClass(l.get(i).getName(),l.get(i).getRegisteredBy(),l.get(i).getPic(),l.get(i).getDetails(),
                            l.get(i).getElerhetoseg());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                d.add(tmp);



        }

        System.out.println(d.size());
        list.addAll(d);
        table.setItems(list);












    }
    @FXML
    public void choice_login()
    {
        outerlayer.setVisible(false);
        loginlayer.setVisible(true);



    }

    public void choice_register()
    {

        loginlayer.setVisible(false);
        registerlayer.setVisible(true);



    }























    public void registerSequence()
    {
        if(regFullname.getLength()==0|| regPassword.getLength()==0|| regUsername.getLength()==0)
        {
            regerror.setText("Hibás mező(k)!");

        }
        else if(regPassword.getLength()<8)regerror.setText("A jelszónak legalább 8 karakter hosszúnak kell lennie!");
        else
        {
            if(UserRepo.findByUsername(regUsername.getText())!=null)
            {
                regerror.setText("Már létezik felhasználó ilyen felhasználói névvel");
            }
            else
            {
                RegisteredUser n = new RegisteredUser();
                n.setPassword(regPassword.getText());
                n.setName(regFullname.getText());
                n.setUsername(regUsername.getText());
                UserRepo.save(n);
                registerlayer.setVisible(false);
                loginlayer.setVisible(true);
                cantlogin.setText("Sikeres regisztráció");
                try {
                    FileWriter writer = new FileWriter("src/users.txt", true);
                    writer.write(n.getId()+","+n.getName()+","+n.getUsername()+","+n.getPassword()+"\n");

                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



        }


    }






    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col1.setCellValueFactory(new PropertyValueFactory<>("picture"));
        col2.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        col3.setCellValueFactory(
                new PropertyValueFactory<>("registeredBy"));
        col4.setCellValueFactory(
                new PropertyValueFactory<>("details"));
        col5.setCellValueFactory(
                new PropertyValueFactory<>("elerhetoseg"));
        try {
            Scanner read = new Scanner(new File("src/database.txt"));
            int count =0;
            while (read.hasNextLine())
            {
                String s = read.nextLine();
                count++;
            }
            top=count/11;



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }







        outerlayer.prefHeightProperty().bind(anchor.heightProperty());
        outerlayer.prefWidthProperty().bind(anchor.widthProperty());
        addlayer.prefHeightProperty().bind(anchor.heightProperty());
        addlayer.prefWidthProperty().bind(anchor.widthProperty());
        searchlayer.prefHeightProperty().bind(anchor.heightProperty());
        searchlayer.prefWidthProperty().bind(anchor.widthProperty());
        registerlayer.prefHeightProperty().bind(anchor.heightProperty());
        registerlayer.prefWidthProperty().bind(anchor.widthProperty());
        loginlayer.prefHeightProperty().bind(anchor.heightProperty());
        loginlayer.prefWidthProperty().bind(anchor.widthProperty());
        double l1X=l1.getLayoutX(),l2X=l2.getLayoutX(),l3X=l3.getLayoutX(),l4X=l4.getLayoutX(),
                l5X=l5.getLayoutX(),l6X=l6.getLayoutX(),l7X=l7.getLayoutX(),l8X=l8.getLayoutX(),
                l9X=l9.getLayoutX(),l10X=l10.getLayoutX(),l11X=l11.getLayoutX(),l12X=l12.getLayoutX();
        double l1Y=l1.getLayoutY(),l2Y=l2.getLayoutY(),l3Y=l3.getLayoutY(),l4Y=l4.getLayoutY(),
                l5Y=l5.getLayoutY(),l6Y=l6.getLayoutY(),l7Y=l7.getLayoutY(),l8Y=l8.getLayoutY(),
                l9Y=l9.getLayoutY(),l10Y=l10.getLayoutY(),l11Y=l11.getLayoutY(),l12Y=l12.getLayoutY();
        double d1=carInputName.getLayoutX(),d2=evjaratTEXT.getLayoutX(),d3=hengerurtartalomTEXT.getLayoutX(),
                d4=muszakiTEXT.getLayoutX(),d5=kilometerTEXT.getLayoutX(),d6=ajtokszamaTEXT.getLayoutX(),
                d7=szinTEXT.getLayoutX(),d8=tomegTEXT.getLayoutX(),d9=telefonTEXT.getLayoutX(),
                d10=emailTEXT.getLayoutX(),d11=browse.getLayoutX(),d12=backfromadding.getLayoutX(),
                d13=submitcar.getLayoutX();
        double g1=carInputName.getLayoutY(),g2=evjaratTEXT.getLayoutY(),g3=hengerurtartalomTEXT.getLayoutY(),
                g4=muszakiTEXT.getLayoutY(),g5=kilometerTEXT.getLayoutY(),g6=ajtokszamaTEXT.getLayoutY(),
                g7=szinTEXT.getLayoutY(),g8=tomegTEXT.getLayoutY(),g9=telefonTEXT.getLayoutY(),
                g10=emailTEXT.getLayoutY(),g11=browse.getLayoutY(),g12=backfromadding.getLayoutY(),
                g13=submitcar.getLayoutY();
        double g14=choose1btn.getLayoutY(),g15=choose2btn.getLayoutY(),d14=choose1btn.getLayoutX(),d15=choose2btn.getLayoutX();
        double lx1=username.getLayoutX(),lx2=password.getLayoutX(),lx3=loginbtn.getLayoutX(),lx4=loglab1.getLayoutX(),
                lx5=loglab2.getLayoutX(),lx6=loglab3.getLayoutX(),lx7=backbtn1.getLayoutX(),lx8=cantlogin.getLayoutX(),
                lx9=registerbtn.getLayoutX();
        double ly1=username.getLayoutY(),ly2=password.getLayoutY(),ly3=loginbtn.getLayoutY(),ly4=loglab1.getLayoutY(),
                ly5=loglab2.getLayoutY(),ly6=loglab3.getLayoutY(),ly7=backbtn1.getLayoutY(),ly8=cantlogin.getLayoutY(),
                ly9=registerbtn.getLayoutY();
        double rx1=regFullname.getLayoutX(),rx2=regUsername.getLayoutX(),rx3=regPassword.getLayoutX(),rx4=reglab1.getLayoutX(),
                rx5=reglab2.getLayoutX(),rx6=reglab3.getLayoutX(),rx7=regbtn.getLayoutX(),rx8=regback.getLayoutX(),
                rx9=regerror.getLayoutX();
        double ry1=regFullname.getLayoutY(),ry2=regUsername.getLayoutY(),ry3=regPassword.getLayoutY(),ry4=reglab1.getLayoutY(),
                ry5=reglab2.getLayoutY(),ry6=reglab3.getLayoutY(),ry7=regbtn.getLayoutY(),ry8=regback.getLayoutY(),
                ry9=regerror.getLayoutY();
        double sx1=backsearch.getLayoutX(),sx2=table.getLayoutX(),sx3=searchinput.getLayoutX();
        double sy1=backsearch.getLayoutY(),sy2=table.getLayoutY(),sy3=searchinput.getLayoutY();
        double tablescaleX=table.getPrefWidth(),tablescaleY=table.getPrefHeight();
        double mycX=tomycars.getLayoutX(),mycY=tomycars.getLayoutY();
        double rpwX=resetpwbtn.getLayoutX(),rpwY=resetpwbtn.getLayoutY();












        ChangeListener<? super Number> changeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double scale=0;
                if(oldValue.intValue()!=0)scale = newValue.doubleValue()/750.0;


                if(scale!=0 )
                {
                    choose1btn.setLayoutY(g14*scale);
                    choose2btn.setLayoutY(g15*scale);
                    ////////////////////////////////////
                    l1.setLayoutY(l1Y*scale);
                    l2.setLayoutY(l2Y*scale);
                    l3.setLayoutY(l3Y*scale);
                    l4.setLayoutY(l4Y*scale);
                    l5.setLayoutY(l5Y*scale);
                    l6.setLayoutY(l6Y*scale);
                    l7.setLayoutY(l7Y*scale);
                    l8.setLayoutY(l8Y*scale);
                    l9.setLayoutY(l9Y*scale);
                    l10.setLayoutY(l10Y*scale);
                    l11.setLayoutY(l11Y*scale);
                    l12.setLayoutY(l12Y*scale);
                    carInputName.setLayoutY(g1*scale);
                    evjaratTEXT.setLayoutY(g2*scale);
                    hengerurtartalomTEXT.setLayoutY(g3*scale);
                    muszakiTEXT.setLayoutY(g4*scale);
                    kilometerTEXT.setLayoutY(g5*scale);
                    ajtokszamaTEXT.setLayoutY(g6*scale);
                    szinTEXT.setLayoutY(g7*scale);
                    tomegTEXT.setLayoutY(g8*scale);
                    telefonTEXT.setLayoutY(g9*scale);
                    emailTEXT.setLayoutY(g10*scale);
                    browse.setLayoutY(g11*scale);
                    backfromadding.setLayoutY(g12*scale);
                    submitcar.setLayoutY(g13*scale);
                   ////////////////////////////////////////////
                    username.setLayoutY(ly1*scale);
                    password.setLayoutY(ly2*scale);
                    loginbtn.setLayoutY(ly3*scale);
                    loglab1.setLayoutY(ly4*scale);
                    loglab2.setLayoutY(ly5*scale);
                    loglab3.setLayoutY(ly6*scale);
                    backbtn1.setLayoutY(ly7*scale);
                    cantlogin.setLayoutY(ly8*scale);
                    registerbtn.setLayoutY(ly9*scale);


                    ////////////////////////////////////////////
                    regFullname.setLayoutY(ry1*scale);
                    regUsername.setLayoutY(ry2*scale);
                    regPassword.setLayoutY(ry3*scale);
                    reglab1.setLayoutY(ry4*scale);
                    reglab2.setLayoutY(ry5*scale);
                    reglab3.setLayoutY(ry6*scale);
                    regbtn.setLayoutY(ry7*scale);
                    regback.setLayoutY(ry8*scale);
                    regerror.setLayoutY(ry9*scale);
                    ////////////////////////////////////
                    backsearch.setLayoutY(sy1*scale);
                    table.setLayoutY(sy2*scale);
                    searchinput.setLayoutY(sy3*scale);
                    /////////////////////////////////////
                    table.setPrefHeight(tablescaleY*scale);
                    tomycars.setLayoutY(mycY*scale);
                    resetpwbtn.setLayoutY(rpwY*scale);





                }


            }
        };


        ChangeListener<? super Number> changeListener2 = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double scale=0;
                if(oldValue.intValue()!=0)scale = newValue.doubleValue()/1000.0;

                if(scale!=0 )
                {
                    choose1btn.setLayoutX(d14*scale);
                    choose2btn.setLayoutX(d15*scale);
                    ////////////////////////////////////////////
                    l1.setLayoutX(l1X*scale);
                    l2.setLayoutX(l2X*scale);
                    l3.setLayoutX(l3X*scale);
                    l4.setLayoutX(l4X*scale);
                    l5.setLayoutX(l5X*scale);
                    l6.setLayoutX(l6X*scale);
                    l7.setLayoutX(l7X*scale);
                    l8.setLayoutX(l8X*scale);
                    l9.setLayoutX(l9X*scale);
                    l10.setLayoutX(l10X*scale);
                    l11.setLayoutX(l11X*scale);
                    l12.setLayoutX(l12X*scale);
                    carInputName.setLayoutX(d1*scale);
                    evjaratTEXT.setLayoutX(d2*scale);
                    hengerurtartalomTEXT.setLayoutX(d3*scale);
                    muszakiTEXT.setLayoutX(d4*scale);
                    kilometerTEXT.setLayoutX(d5*scale);
                    ajtokszamaTEXT.setLayoutX(d6*scale);
                    szinTEXT.setLayoutX(d7*scale);
                    tomegTEXT.setLayoutX(d8*scale);
                    telefonTEXT.setLayoutX(d9*scale);
                    emailTEXT.setLayoutX(d10*scale);
                    browse.setLayoutX(d11*scale);
                    backfromadding.setLayoutX(d12*scale);
                    submitcar.setLayoutX(d13*scale);
                    ////////////////////////////////////////////
                    username.setLayoutX(lx1*scale);
                    password.setLayoutX(lx2*scale);
                    loginbtn.setLayoutX(lx3*scale);
                    loglab1.setLayoutX(lx4*scale);
                    loglab2.setLayoutX(lx5*scale);
                    loglab3.setLayoutX(lx6*scale);
                    backbtn1.setLayoutX(lx7*scale);
                    cantlogin.setLayoutX(lx8*scale);
                    registerbtn.setLayoutX(lx9*scale);
                    ////////////////////////////////////////////
                    regFullname.setLayoutX(rx1*scale);
                    regUsername.setLayoutX(rx2*scale);
                    regPassword.setLayoutX(rx3*scale);
                    reglab1.setLayoutX(rx4*scale);
                    reglab2.setLayoutX(rx5*scale);
                    reglab3.setLayoutX(rx6*scale);
                    regbtn.setLayoutX(rx7*scale);
                    regback.setLayoutX(rx8*scale);
                    regerror.setLayoutX(rx9*scale);
                    /////////////////////////////////////////
                    backsearch.setLayoutX(sx1*scale);
                    table.setLayoutX(sx2*scale);
                    searchinput.setLayoutX(sx3*scale);
                    ////////////////////////////////////
                    table.setPrefWidth(tablescaleX*scale);
                    tomycars.setLayoutX(mycX*scale);
                    resetpwbtn.setLayoutX(rpwX*scale);




                }


            }
        };
        anchor.heightProperty().addListener((ChangeListener<? super Number>) changeListener);
        anchor.widthProperty().addListener((ChangeListener<? super Number>) changeListener2);



    }


    public void toMyCars()
    {
        addlayer.setVisible(false);
        myCarslayer.setVisible(true);
    }

    public void backToAddLayer()
    {
        resetpwLayer.setVisible(false);
        myCarslayer.setVisible(false);
        addlayer.setVisible(true);
    }
    public void toresetpw()
    {
        addlayer.setVisible(false);
        resetpwLayer.setVisible(true);
    }















}
