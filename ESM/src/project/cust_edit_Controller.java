package project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.io.IOException;

import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class cust_edit_Controller {

    ////////// VARIABLES FROM FXML /////////////////

    @FXML private JFXTextField fname;
    @FXML private JFXTextField lname;
    @FXML private JFXTextField cur_pass;
    @FXML private JFXTextField new_pass;
    @FXML private JFXTextField email;
    @FXML private JFXTextField account;
    @FXML private JFXTextField contact;
    @FXML private JFXTextField age;

    @FXML private JFXButton exit_btn;
    @FXML private JFXButton edit_btn;

    //////////////////////////////////////////////

    public void init() {
        System.out.println("Initialising customer edit variables");

        Customer cust = LoggedInUsers.getCust();

        // set values
        String firstName, lastName;
        String fullName = cust.getName();
        String[] tokens = fullName.split(" ", 2);
        firstName = tokens[0];
        lastName = tokens[1];

        fname.setText(firstName);
        lname.setText(lastName);
        age.setText(Integer.toString(cust.getAge()));
        account.setText(cust.getAccount_number());
        email.setText(cust.getEmail_address());
        contact.setText(cust.getContact_info());
        // disable editing
        age.setEditable(false);
        email.setEditable(false);
        fname.setEditable(false);
        lname.setEditable(false);
    }

    public boolean checkInputs() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        // null values
        if (account == null || account.getText().isEmpty()) {
            openPopup("Missing Input", "Account number should not be blank.");
            return false;
        }

        // account no has letters
        if (account.getText().matches(".*[a-zA-Z]+.*")) {
            openPopup("Invalid Input", "Account number should only contain numbers.");
            return false;
        }

        // account no is negative
        if (Integer.parseInt(account.getText()) < 0) {
            openPopup("Invalid Input", "Account number cannot be negative.");
            return false;
        }

        if(contact.getText().matches(".*[a-zA-Z]+.*")){
            openPopup("Invalid Input", "Account number should only contain numbers");
            return false;
        }

        return true;
    }

    //////////////////////////////////////////////////////

    // called when "save changes" button pressed
    public void handleEditButtonAction() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("Edit button pressed");

        if (!checkInputs()) {
            System.out.println("Input check failed");
            return;
        }

        String c_fname = "", c_lname = "", c_newpass = "", c_curpass = "", c_email = "", c_account = "", c_contact = "";

        // get non-empty values
        if (fname != null) {
            c_fname = fname.getText();
        }
        if (lname != null) {
            c_lname = lname.getText();
        }
        if (new_pass != null) {
            c_newpass = new_pass.getText();
        }
        if (cur_pass != null) {
            c_curpass = cur_pass.getText();
        }
        if (email != null) {
            c_email = email.getText();
        }
        if (account != null) {
            c_account = account.getText();
        }
        if(contact != null){
            c_contact = contact.getText();
        }
        int c_age = Integer.parseInt(age.getText());

        // editing customer
        Customer cust = new Customer();
        cust.setName(c_fname + " " + c_lname);
        cust.setEmail_address(c_email);
        cust.setAccount_number(c_account);
        cust.setAge(c_age);
        cust.setContact_info(c_contact);
        // getting id
        String id = LoggedInUsers.getCust_id();
        System.out.println("id = " + id);

        // updating values in database and in logged in user state

        if (!cust.getName().isEmpty()) {
            cust.editCustomerAccountField(id,"name", cust.getName());
            LoggedInUsers.getCust().setName(cust.getName());
        }

        if (!cust.getAccount_number().isEmpty()) {
            cust.editCustomerAccountField(id,"account_number", cust.getAccount_number());
            LoggedInUsers.getCust().setAccount_number(cust.getAccount_number());
        }
        if(!cust.getContact_info().isEmpty()){
            cust.editCustomerAccountField(id, "phone_no", cust.getContact_info());
            LoggedInUsers.getCust().setContact_info(cust.getContact_info());
        }

        if (!c_curpass.isEmpty() && !c_newpass.isEmpty()) {
            if(cust.changePassword(id, c_curpass, c_newpass)) {
                emailClass.sendEmail("Password Changed","Your password has been changed successfully. If this was not done by you, immediately contact us at asheventshelp@gmail.com", LoggedInUsers.getCust().getEmail_address());
            }
            else {
                openPopup("Warning","Password not changed.");
            }
        }

        // refresh data
        LoggedInUsers.setCust(cust);

        // notify customer account was edited
        String msg = "Your account details have been changed successfully. If this was not done by you, then please contact us at asheventshelp@gmail.com";
        emailClass.sendEmail("Account Info Updated", msg, c_email);

        // refresh screen
        goToEditScreen();
    }

    // called when exit X button pressed
    public void handleExitButton() throws IOException {
        System.out.println("Exit button pressed");
        goToCustMenu();
    }

    ///////////////////// SCENE SWITCHING  //////////////////////////


    public void goToCustMenu() throws IOException {
        System.out.println("Loading customer menu window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cust_menu.fxml"));
        Parent root = loader.load();

        //Get controller of menu scene
        cust_menu_Controller controller = loader.getController();

        //setting information
        controller.setName(LoggedInUsers.getCust().getName());
        controller.setEventBookedStatus(LoggedInUsers.getCust_id());

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

        window.setTitle("Welcome");
        window.show();
    }

    public void goToEditScreen() throws IOException {
        System.out.println("Loading edit window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cust_edit.fxml"));
        Parent root = loader.load();

        //Get controller of edit scene
        cust_edit_Controller controller = loader.getController();
        controller.init();

        // close current window
        Stage window = (Stage) edit_btn.getScene().getWindow();
        window.close();

        // start new window for edit scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Editing Your Account");
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
