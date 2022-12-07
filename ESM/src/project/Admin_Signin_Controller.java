package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Admin_Signin_Controller {

    @FXML private JFXRadioButton admin_btn;
    @FXML private JFXButton signin_btn;
    @FXML private JFXButton exit_btn;

    /////// VARIABLES ///////////

    @FXML private JFXTextField emp_id;
    @FXML private JFXTextField emp_pass;

    ///////////////////////////////////////////////////

    public void enableAdminButton() {
        admin_btn.setSelected(true);
        admin_btn.setDisable(true);
        admin_btn.setOpacity(1);
    }

    ///////////////////////////////////////////////////

    public void handleSignInButton(ActionEvent actionEvent) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("Sign in button pressed (Admin)");

        String id = emp_id.getText();
        String password = emp_pass.getText();

        Employee emp = new Employee();
        boolean login = emp.employeeLogin(id, password);

        //close login window if login unsuccessful - to be changed
        if (login == false) {
            openPopup("Login Failed", "Invalid email/ID and password combination.");
            System.out.println("Login unsuccessful");
        }

        // Login successful - go to welcome screen
        else {
            // check if employee is an admin
            if (emp.isAdmin(id)) {
                System.out.println("Login successful");
                LoggedInUsers.initEmp(id);

                // send email
                String msg = "You have just signed in to our system.";
                emailClass.sendEmail("Successful Login", msg, LoggedInUsers.getEmp().getEmail());

                goToAdminMenu();
            }

            else {
                openPopup("Warning", "No manager exists against the provided ID.");
            }
        }
    }

    public void handleExitButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Exit button pressed");
        goToMainMenu();
    }

    ///////////////////// SCENE SWITCHING //////////////////

    public void goToMainMenu() throws IOException {
        System.out.println("Loading menu window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        Parent root = loader.load();

        //Get controller of menu scene
        mainmenucont controller = loader.getController();

        // close current window
        Stage window = (Stage) exit_btn.getScene().getWindow();
        window.close();

        // start new window for menu scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Main Menu");
        window.show();
    }

   public void goToAdminMenu() throws IOException {
        System.out.println("Loading manager menu window");

      //  Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_menu.fxml"));
       Parent root = loader.load();

        // Get controller of manager menu scene
        Admin_menu_Controller controller = loader.getController();
        controller.setName(LoggedInUsers.getEmp().getName());

        // close current window
      Stage window = (Stage) exit_btn.getScene().getWindow();
       window.close();

        // start new window for admin scene
      window = new Stage();
      window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

      Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
      Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

      window.setTitle("Admin Menu");
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