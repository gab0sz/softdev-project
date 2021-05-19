package fx.controller;

import hu.unideb.inf.java.fx.example.controller.FXController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FXControllerTest {

    @Test
    public void testevjaratRestrict(){
        FXController fxc = new FXController();
        fxc.getEvjaratTEXT().setText("1926");

        fxc.evjaratRestrict();
        String s = fxc.getEvjaratTEXT().getText();

        Assertions.assertEquals("1926",s);
    }


}
