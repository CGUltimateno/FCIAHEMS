package project;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class sp_menu_Controller {

    @FXML private Label welcome;

    @FXML private JFXButton event_btn;
    @FXML private JFXButton edit_btn;
    @FXML private JFXButton exit_btn;

    //////////////////////////////////////////////////////

    // display user's name
    public void setName(String str) {
        String first_name = str.contains(" ") ? str.split(" ")[0] : str;
        welcome.setText("Welcome, " + first_name);
    }

    ///////////////////////////////////////////////////////

    public void handleEventButton() throws IOException {
        System.out.println("Event button pressed");
        goToEventOptions();
    }

    public void handleEditButton() throws IOException {
        System.out.println("Edit button pressed");
        goToEditOptions();
    }

    public void handleExitButton() throws IOException {
        System.out.println("Exit button pressed");
        LoggedInUsers.clearEmp();
        goToSpSignIn();
    }

    /////////////// SCENE SWITCHING /////////////////////

    public void goToSpSignIn() throws IOException {
        System.out.println("Loading Sp sign in window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sp_signin.fxml"));
        Parent root = loader.load();

        // close current window
        Stage window = (Stage) exit_btn.getScene().getWindow();
        window.close();

        // start new window for sign in scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Sign In");
        window.show();
    }

    public void goToEventOptions() throws IOException {
        System.out.println("Loading event options window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sp_view_approved.fxml"));
        Parent root = loader.load();

        // close current window
        Stage window = (Stage) event_btn.getScene().getWindow();
        window.close();

        // start new window for event scene
        window = new Stage();
        Scene scene = new Scene(root,900,600);
        window.setScene(scene);

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        scene.getStylesheets().add(getClass().getResource("css/events_table_style.css").toExternalForm());
        window.setTitle("View Events");
        window.show();
    }
    public void goToEditOptions() throws IOException {
        System.out.println("Loading edit options window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sp_edit.fxml"));
        Parent root = loader.load();

        //Get controller of edit scene
        sp_edit_Controller controller = loader.getController();
        controller.init();

        // close current window
        Stage window = (Stage) exit_btn.getScene().getWindow();
        window.close();

        // start new window for edit scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Edit Your Account");
        window.show();
    }
}
