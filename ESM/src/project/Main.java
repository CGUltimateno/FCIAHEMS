package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    Stage w;
    @Override
public void start(Stage primaryStage) throws Exception{
        System.out.println("Loading...");
 // MAIN MENU  //
        w = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        w.setTitle("Event Management System");
        w.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/LatoLight.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/CarryYouRegular.ttf"), 10);

        w.show();
    }
    public void setScene(Scene scene) {
        w.setScene(scene);
    }

    public static void setSQL() {
        Database.setDb_name("database");
        Database.setDb_user("root");
        Database.setDb_pass("12345");
    }
    public static void setEmailDetails() {
        emailClass.init(System.getProperties(), "mohameddeaf65@gmail.com", "securepassword");
    }
    public static void main(String[] args) {
        setSQL();
        setEmailDetails();
        launch(args);
    }
}