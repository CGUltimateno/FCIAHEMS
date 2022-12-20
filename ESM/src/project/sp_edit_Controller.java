package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
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
import java.time.format.DateTimeFormatter;

public class sp_edit_Controller extends sp_menu_Controller{

    @FXML private JFXTextField fname;
    @FXML private JFXTextField lname;
    @FXML private JFXTextField accountno;
    @FXML private JFXTextField newpass;
    @FXML private JFXTextField curpass;
    @FXML private JFXTextField sp_id;
    @FXML private JFXTextField contact;

    //////////// BUTTONS /////////

    @FXML private JFXButton exit_btn;
    @FXML private JFXButton save_btn;

    //////////////////////////////////////////////////////////////////////////

    public void init() {
        System.out.println("Initialising edit info variables");

        ServiceProvider sp = LoggedInUsers.getsp();

        // set values
        contact.setText(sp.getPhone_no());
        // get values
        String firstName = "", lastName = "";
        String fullName = sp.getName();
        String[] tokens = fullName.split(" ", 2);
        firstName = tokens[0];
        lastName = tokens[1];


        // set values
        fname.setText(firstName);
        lname.setText(lastName);

        accountno.setText(sp.getAccount_number());

        // disable editing
        sp_id.setEditable(false);
    }

    public boolean checkInputs() throws IOException, LineUnavailableException, UnsupportedAudioFileException {

        // check for null values
        if (fname == null || accountno == null) {
            openPopup("Invalid Input", "Please fill all the fields.");
            return false;
        }

        // check for blank values
        if (fname.getText().isEmpty()|| accountno.getText().isEmpty()) {
            openPopup("Invalid Input", "Please fill all the fields.");
            return false;
        }

        // null values or blank values
        if (contact == null || contact.getText().isEmpty()) {
            openPopup("Missing Input", "Contact number should not be blank.");
            return false;
        }

        return true;
    }

    //////////////////////////////////////////////////////

    public void handleExitButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Exit button pressed.");
        goToSPMenu();
    }

    public void handleSaveButton(ActionEvent actionEvent) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("Save button pressed");

        if (!checkInputs()) {
            System.out.println("Input check failed");
            return;
        }

        // getting all the data entered
        String sp_name = fname.getText() + " " + lname.getText();
        String sp_acc = accountno.getText();
        String sp_contact = contact.getText();

        // setting values
        ServiceProvider sp = new ServiceProvider(LoggedInUsers.getsp());
        sp.setName(sp_name);
        sp.setAccount_number(sp_acc);
        sp.setPhone_no(sp_contact);

        // updating record in database
        sp.editServiceProviderAccountField(sp.getsp_id(),"name",sp_name, false);
        sp.editServiceProviderAccountField(sp.getAccount_number(),"account_number",sp_acc, false);
        sp.editServiceProviderAccountField(sp.getPhone_no(), "phone_no", sp_contact, false);


        if (curpass != null && newpass != null) {
            String currPass = curpass.getText();
            String newPass = newpass.getText();

            if (!currPass.isEmpty() && !newPass.isEmpty()){

                // password changed successfully
                if (sp.changePassword(currPass, newPass)) {
                    System.out.println("Password changed");
                }

                // password not changed
                else {
                    openPopup("Warning","Password not changed.");
                }
            }
        }
        // refresh data
        LoggedInUsers.setsp(sp);

        // refresh screen
        goToSPEdit();
    }

    //////////////// SCENE SWITCHING ///////////////////

    public void goToSPMenu() throws IOException {
        System.out.println("Loading manager menu window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sp_menu.fxml"));
        Parent root = loader.load();

        //Get controller of menu scene
        sp_menu_Controller controller = loader.getController();
        controller.setName(LoggedInUsers.getsp().getName());

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

        window.setTitle("SP Menu");
        window.show();
    }

    public void goToSPEdit() throws IOException {
        System.out.println("Loading SP edit window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sp_edit.fxml"));
        Parent root = loader.load();

        //Get controller of manager edit scene
        sp_edit_Controller controller = loader.getController();
        controller.init();

        // close current window
        Stage window = (Stage) save_btn.getScene().getWindow();
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
