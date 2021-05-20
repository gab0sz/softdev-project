package hu.unideb.inf.java.jpa.example;

import hu.unideb.inf.java.jpa.example.config.AppConfig;
import hu.unideb.inf.java.jpa.example.init.DataBaseInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class App extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DataBaseInitializer db = context.getBean(DataBaseInitializer.class);
        db.init();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        loader.setControllerFactory(context::getBean);
        showStage(loader.load(), stage);
    }
    
    
    private void showStage(Parent root, Stage stage) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("IT-ERA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
