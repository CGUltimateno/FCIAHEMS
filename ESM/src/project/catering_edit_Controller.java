package project;

import com.jfoenix.controls.JFXButton;
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
import java.util.ArrayList;
import java.util.HashMap;

public class catering_edit_Controller {

    private ArrayList<String> caterIDs;

    ////////// cater 1 VARS /////////

    @FXML private JFXTextField cater1_name;
    @FXML private JFXTextField cater1_contact;
    @FXML private JFXTextField cater1_specialty;
    @FXML private JFXTextField cater1_price;
    @FXML private JFXTextField cater1_days;

    ////////// cater 2 VARS /////////

    @FXML private JFXTextField cater2_name;
    @FXML private JFXTextField cater2_contact;
    @FXML private JFXTextField cater2_specialty;
    @FXML private JFXTextField cater2_price;
    @FXML private JFXTextField cater2_days;

    ////////// cater 3 VARS /////////

    @FXML private JFXTextField cater3_name;
    @FXML private JFXTextField cater3_contact;
    @FXML private JFXTextField cater3_specialty;
    @FXML private JFXTextField cater3_price;
    @FXML private JFXTextField cater3_days;

    ///////////// BUTTONS //////////////

    @FXML private JFXButton exit_btn;
    @FXML private JFXButton save_btn;

    //////////////////////////////////////////////

    // set values to be displayed
    public void init() {
        System.out.println("Initialising catering edit window");

        // get list of all caters
        Catering_Service catering_obj = new Catering_Service();

        HashMap<ArrayList<String>, ArrayList<Catering_Service>> PairOfcatersAndIDs = catering_obj.getListOfCaterersAndIDs();
        // at first index of hashmap
        ArrayList<Catering_Service> caterList = (ArrayList<Catering_Service>) PairOfcatersAndIDs.values().toArray()[0];
        caterIDs = (ArrayList<String>) PairOfcatersAndIDs.keySet().toArray()[0];

        // picking up all three caters from database
        Catering_Service c1 = caterList.get(0);
        Catering_Service c2 = caterList.get(1);
        Catering_Service c3 = caterList.get(2);

        /// transferring to interface variables

        // first cater details
        cater1_name.setText(c1.getCompany_name());
        cater1_contact.setText(c1.getContact_info());
        cater1_specialty.setText(c1.getSpeciality());
        cater1_price.setText(Integer.toString(c1.getCharges()));
        cater1_days.setText(Integer.toString(c1.getDays()));

        // second cater details
        cater2_name.setText(c2.getCompany_name());
        cater2_contact.setText(c2.getContact_info());
        cater2_specialty.setText(c2.getSpeciality());
        cater2_price.setText(Integer.toString(c2.getCharges()));
        cater2_days.setText(Integer.toString(c2.getDays()));

        // second cater details
        cater3_name.setText(c3.getCompany_name());
        cater3_contact.setText(c3.getContact_info());
        cater3_specialty.setText(c3.getSpeciality());
        cater3_price.setText(Integer.toString(c3.getCharges()));
        cater3_days.setText(Integer.toString(c3.getDays()));
    }

    public boolean checkInputs() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        // null values
        if (cater1_name == null || cater1_contact == null || cater1_specialty == null || cater1_price == null || cater1_days == null) {
            openPopup("Missing Input", "Please fill all the fields for 1st cater.");
            return false;
        }

        if (cater2_name == null || cater2_contact == null || cater2_specialty == null || cater2_price == null || cater2_days == null) {
            openPopup("Missing Input", "Please fill all the fields for 2nd cater.");
            return false;
        }

        if (cater3_name == null || cater3_contact == null || cater3_specialty == null || cater3_price == null || cater3_days == null) {
            openPopup("Missing Input", "Please fill all the fields for 2nd cater.");
            return false;
        }

        // empty values
        if (cater1_name.getText().isEmpty()|| cater1_contact.getText().isEmpty() || cater1_specialty.getText().isEmpty() || cater1_price.getText().isEmpty() || cater1_days.getText().isEmpty()) {
            openPopup("Missing Input", "Please fill all the fields for 1st cater.");
            return false;
        }

        if (cater2_name.getText().isEmpty() || cater2_contact.getText().isEmpty()|| cater2_specialty.getText().isEmpty() || cater2_price.getText().isEmpty() || cater2_days.getText().isEmpty()) {
            openPopup("Missing Input", "Please fill all the fields for 2nd cater.");
            return false;
        }

        if (cater3_name.getText().isEmpty() || cater3_contact.getText().isEmpty() || cater3_specialty.getText().isEmpty() || cater3_price.getText().isEmpty() || cater3_days.getText().isEmpty()) {
            openPopup("Missing Input", "Please fill all the fields for 2nd cater.");
            return false;
        }

        // letters in price
        if (cater1_price.getText().matches(".*[a-zA-Z]+.*") || cater2_price.getText().matches(".*[a-zA-Z]+.*") || cater3_price.getText().matches(".*[a-zA-Z]+.*")) {
            openPopup("Missing Input", "All prices must be numbers.");
            return false;
        }

        // letters in days
        if (cater1_days.getText().matches(".*[a-zA-Z]+.*") || cater2_days.getText().matches(".*[a-zA-Z]+.*") || cater3_days.getText().matches(".*[a-zA-Z]+.*")) {
            openPopup("Missing Input", "All days must be in numbers.");
            return false;
        }

        // negative prices
        int c1_price = Integer.parseInt(cater1_price.getText());
        int c2_price = Integer.parseInt(cater2_price.getText());
        int c3_price = Integer.parseInt(cater3_price.getText());

        if ( c1_price < 0 || c2_price < 0 || c3_price < 0 ) {
            openPopup("Invalid Input", "Prices cannot be negative");
            return false;
        }

        return true;
    }

    ////////////////////////////////////////////////

    // exit this screen, go back to Admin menu
    public void handleExitButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Exit button pressed");
        gotoAdminmenu();
    }

    public void handleSaveButton(ActionEvent actionEvent) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("Save button pressed");

        if (checkInputs() == false) {
            System.out.println("Input check failed");
            return;
        }

        String name, contact, specialty, price, days;
        Catering_Service cater = new Catering_Service();

        // getting values entered
        name = cater1_name.getText();
        contact = cater1_contact.getText();
        specialty = cater1_specialty.getText();
        price = cater1_price.getText();
        days = cater1_days.getText();

        // updating records in database
        cater.editCateringField(caterIDs.get(0),"name",name,false);
        cater.editCateringField(caterIDs.get(0),"contact",contact,false);
        cater.editCateringField(caterIDs.get(0),"specialty",specialty,false);
        cater.editCateringField(caterIDs.get(0),"days", days, true);
        cater.editCateringField(caterIDs.get(0), "days", days, true);

        // getting values entered
        name = cater2_name.getText();
        contact = cater2_contact.getText();
        specialty = cater2_specialty.getText();
        price = cater2_price.getText();
        days = cater2_days.getText();

        // updating records in database
        cater.editCateringField(caterIDs.get(1),"name",name,false);
        cater.editCateringField(caterIDs.get(1),"contact",contact,false);
        cater.editCateringField(caterIDs.get(1),"specialty",specialty,false);
        cater.editCateringField(caterIDs.get(1),"days", days, true);
        cater.editCateringField(caterIDs.get(1),"charges",price,true);

        // getting values stored
        name = cater3_name.getText();
        contact = cater3_contact.getText();
        specialty = cater3_specialty.getText();
        price = cater3_price.getText();
        days = cater3_days.getText();

        // getting values entered
        cater.editCateringField(caterIDs.get(2),"name",name,false);
        cater.editCateringField(caterIDs.get(2),"contact",contact,false);
        cater.editCateringField(caterIDs.get(2),"specialty",specialty,false);
        cater.editCateringField(caterIDs.get(2),"days", days, true);
        cater.editCateringField(caterIDs.get(2),"charges",price,true);

        // refresh window
        goToCateringOptions();
    }

    ///////////////// SCENE SWITCHING //////////////////

    public void gotoAdminmenu() throws IOException {
        System.out.println("Loading Admin menu window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_menu.fxml"));
        Parent root = loader.load();

        //Get controller of Admin menu scene
        Admin_menu_Controller controller = loader.getController();
        controller.setName(LoggedInUsers.getEmp().getName());

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

        window.setTitle("Admin Menu");
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
        Stage window = (Stage) save_btn.getScene().getWindow();
        window.close();

        // start new window for sign in scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Edit Catering Services");
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
