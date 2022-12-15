package project;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class mgr_menu_Controller {

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

    public void handleEventButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Event button pressed");
        goToEventOptions();
    }

    public void handleEditButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Edit button pressed");
        goToEditOptions();
    }

    public void handleExitButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Exit button pressed");
        LoggedInUsers.clearEmp();
        goToMgrSignIn();
    }

    /////////////// SCENE SWITCHING /////////////////////

    public void goToMgrSignIn() throws IOException {
        System.out.println("Loading manager sign in window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mgr_signin.fxml"));
        Parent root = loader.load();

        //Get controller of sign in scene
        mgr_signin_Controller controller = loader.getController();
        controller.enableManagerButton();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mgr_view_events.fxml"));
        Parent root = loader.load();

        //Get controller of event scene
        mgr_view_events_Controller controller = loader.getController();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mgr_edit.fxml"));
        Parent root = loader.load();

        //Get controller of edit scene
        mgr_edit_Controller controller = loader.getController();
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
