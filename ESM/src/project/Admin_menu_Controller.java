package project;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin_menu_Controller {

    @FXML private Label welcome;

    @FXML private JFXButton event_btn;
    @FXML private JFXButton venue_btn;
    @FXML private JFXButton catering_btn;
    @FXML private JFXButton studio_btn;
    @FXML private JFXButton emp_btn;
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


    public void handleVenueButton() throws IOException {
        System.out.println("Venue button pressed");
        goToVenueOptions();
    }

    public void handleCateringButton() throws IOException {
        System.out.println("Catering button pressed");
        goToCateringOptions();
    }

    public void handleStudioButton() throws IOException {
        System.out.println("Studio button pressed");
        goToStudioOptions();
    }

    public void handleEmpButton() throws IOException {
        System.out.println("Employee button pressed");
        goToEmpOptions();
    }

    public void handleEditButton() throws IOException {
        System.out.println("Edit button pressed");
        goToEditOptions();
    }

    public void handleExitButton() throws IOException {
        System.out.println("Exit button pressed");
        LoggedInUsers.clearEmp();
        goToAdminSignIn();
    }

    /////////////// SCENE SWITCHING /////////////////////

    public void goToAdminSignIn() throws IOException {
        System.out.println("Loading Admin sign in window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adm_signin.fxml"));
        Parent root = loader.load();

        //Get controller of sign in scene
        Admin_Signin_Controller controller = loader.getController();
        controller.enableAdminButton();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_view_events.fxml"));
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

    public void goToVenueOptions() throws IOException {
        System.out.println("Loading venue options window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("venue_edit.fxml"));
        Parent root = loader.load();

        //Get controller of venue scene
        venue_edit_Controller controller = loader.getController();
        controller.init();

        // close current window
        Stage window = (Stage) venue_btn.getScene().getWindow();
        window.close();

        // start new window for venue scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Edit Venues");
        window.show();
    }

    public void goToCateringOptions() throws IOException {
        System.out.println("Loading catering options window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catering_edit.fxml"));
        Parent root = loader.load();

        //Get controller of catering scene
        catering_edit_Controller controller = loader.getController();
        controller.init();

        // close current window
        Stage window = (Stage) catering_btn.getScene().getWindow();
        window.close();

        // start new window for catering scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Edit Catering Services");
        window.show();
    }

    public void goToStudioOptions() throws IOException {
        System.out.println("Loading studio options window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studio_edit.fxml"));
        Parent root = loader.load();

        //Get controller of edit studios scene
        studio_edit_Controller controller = loader.getController();
        controller.init();

        // close current window
        Stage window = (Stage) studio_btn.getScene().getWindow();
        window.close();

        // start new window for edit studios scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Edit Studios");
        window.show();
    }

    public void goToEmpOptions() throws IOException {
        System.out.println("Loading employee options window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_view_emp.fxml"));
        Parent root = loader.load();

        // close current window
        Stage window = (Stage) emp_btn.getScene().getWindow();
        window.close();

        // start new window for edit studios scene
        window = new Stage();
        Scene scene = new Scene(root,1000,665);
        window.setScene(scene);

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        scene.getStylesheets().add(getClass().getResource("css/employees_table_style.css").toExternalForm());
        window.setTitle("View Employees");
        window.show();
    }

    public void goToEditOptions() throws IOException {
        System.out.println("Loading edit options window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_edit.fxml"));
        Parent root = loader.load();

        //Get controller of edit scene
        Admin_edit_Controller controller = loader.getController();
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
