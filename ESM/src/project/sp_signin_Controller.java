package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class sp_signin_Controller {

    /////////// BUTTONS /////////////

    @FXML private JFXButton signin_btn;
    @FXML private JFXButton exit_btn;

    /////// VARIABLES ///////////

    @FXML private JFXTextField sp_id;
    @FXML private JFXTextField sp_pass;

    ////////////////////////////////////////////////////////////

    public boolean checkInputs() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        // null/blank value
        if (sp_id == null || sp_id.getText().isEmpty()) {
            openPopup("Missing Input", "Please enter SP ID.");
            return false;
        }

        // null/blank value
        if (sp_pass == null || sp_pass.getText().isEmpty()) {
            openPopup("Missing Input", "Please enter password.");
            return false;
        }

        return true;
    }

    ///////////////////////////////////////////////////////////////

    public void handleSignInButton() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("Sign in button pressed (Employee)");

        if (!checkInputs()) {
            System.out.println("Input check failed");
            return;
        }

        // get values
        String id = sp_id.getText();
        String password = sp_pass.getText();

        // attempting login
        ServiceProvider sp = new ServiceProvider();
        boolean login = sp.splogin(id, password);

        //close login window if login unsuccessful - to be changed
        if (!login) {
            openPopup("Login Failed", "Invalid email/ID and password combination.");
            System.out.println("Login unsuccessful");
        }

        // Login successful - go to welcome screen
        else {
            // store customer info
            System.out.println("Login successful");
            LoggedInUsers.initsp(id);
            goToSpMenu();
        }
    }

    public void handleExitButton() throws IOException {
        System.out.println("Exit button pressed");
        goToMainMenu();
    }

    ///////////////////// SCENE SWITCHING //////////////////

    public void goToMainMenu() throws IOException {
        System.out.println("Loading main menu window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        Parent root = loader.load();


        // close current window
        Stage window = (Stage) exit_btn.getScene().getWindow();
        window.close();

        // start new window for main scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Main Menu");
        window.show();
    }

    public void goToSpMenu() throws IOException {
        System.out.println("Loading Service Provider menu");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sp_menu.fxml"));
        Parent root = loader.load();

        //Get controller of main menu scene
        sp_menu_Controller controller = loader.getController();
        controller.setName(LoggedInUsers.getsp().getName());

        // close current window
        Stage window = (Stage) signin_btn.getScene().getWindow();
        window.close();

        // start new window for main scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Welcome Service Provider");
        window.show();
    }

    // open popup
    public void openPopup(String heading, String text) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popup.fxml"));
        Parent root = loader.load();

        //Get controller of register scene
        popupcont controller = loader.getController();
        controller.setContent(heading,text);

        // start new window for main scene
        Stage window = new Stage();
        window.setScene(new Scene(root));
        window.show();
    }
}
