package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

public class cust_register_Controller {

    ////////// CUSTOMER REGISTRATION /////////////////

    @FXML private JFXTextField email;
    @FXML private JFXTextField pass;
    @FXML private JFXTextField fname;
    @FXML private JFXTextField lname;

    @FXML private DatePicker dob;

    @FXML private JFXButton exit_btn;

    //////////////////////////////////////////////////

    public boolean checkInputs() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        // check for null values
        if (email == null || pass == null || fname == null || lname == null || dob.getValue() == null) {
            openPopup("Missing Input", "Please fill all the fields.");
            return false;
        }

        // check for empty values
        if (email.getText().isEmpty() || pass.getText().isEmpty() ||  fname.getText().isEmpty()) {
            openPopup("Missing Input", "Please fill all the fields.");
            return false;
        }

        // check if given email is already in use
        if (Customer.checkIfEmailRegistered(email.getText())) { // email is already registered
            openPopup("Missing Input", "An account has already been registered against that email.");
            return false;
        }

        // invalid email address
        if (email.getText().indexOf('@') == -1) {
            openPopup("Invalid Email", "Please enter a valid email.");
            return false;
        }

        return true;
    }

    // called when register button pressed
    public void handleRegisterButtonAction() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("Register button pressed.");

        // user entered incorrect input/no input
        if (!checkInputs()) {
            System.out.println("Input check failed.");
            return;
        }

        // get values entered by user
        String c_email = email.getText();
        String c_pass = pass.getText();
        String c_fname = fname.getText();
        String c_lname = lname.getText();

        // get date from date picker
        LocalDate c_date = dob.getValue();
        int birth_year = c_date.getYear();
        // calculate age
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        int age = current_year - birth_year;

        // age cannot be less than 18 or else children will be booking events
        if (age < 18) {
            openPopup("Warning", "The minimum age requirement to create an account is 18 years old.");
            return;
        }

        // add a customer
        Customer cust = new Customer(c_fname + " " + c_lname, age, "", c_email, "", 1);
        String cid = cust.createCustomerAccountWithPass(c_pass);

        // display successful registration popup
        String text = "Your account has been successfully registered (Customer ID is " + cid + ". An email has been sent to you with your account details.";
        openPopup("Registration Successful", text);

        // send email to customer
        String msg = "Your account has been successfully registered. " +
                    "You can login anytime to access different features. So what are you waiting for? " +
                    "Hurry up and book your first event!\nYour account details are as follows:\nEmail: " + c_email + "\nCustomer ID: " + cid + "\n\n";

        emailClass.sendEmail("Successful Registration", msg, c_email);


        goBackToSignIn();
    }

    public void handleExitButton() throws IOException {
        System.out.println("Exit button pressed");
        goBackToSignIn();
    }

    ////////////////// SCENE SWITCHING ///////////////////////////

    // go back to sign in screen
    public void goBackToSignIn() throws IOException {
        System.out.println("Loading Main Menu window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
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

        window.setTitle("Main Menu");
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
