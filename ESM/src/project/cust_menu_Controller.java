package project;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class cust_menu_Controller {
    @FXML private  Label welcome;

    @FXML private JFXButton edit_btn;
    @FXML private JFXButton viewEvent_btn;
    @FXML private JFXButton bookEvent_btn;
    @FXML private JFXButton signout_btn;

    private boolean hasCustBookedEvent;

    /////////////////////////////////////////////////

    // display name of user
    public void setName(String str) {
        String first_name = str.contains(" ") ? str.split(" ")[0] : str;
        welcome.setText("Welcome, " + first_name);
    }
    public void setEventBookedStatus(String cust_id) {
        Event event = new Event();
        event = event.getEvent(cust_id,1);

        // no event against that cust_id
        hasCustBookedEvent = !event.getName().isEmpty();
    }

    //////////////////////////////////////////////////

    public void handleEditButton() throws IOException {
        System.out.println("Edit button pressed");
        goToEditScreen();
    }

    public void handleSignOutButton() throws IOException {
        System.out.println("Sign out button pressed");
        LoggedInUsers.clearCust();
        goToSignIn();
    }

    public void handleBookButton() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("Book button pressed");

        // customer cannot book another event
        if (hasCustBookedEvent)
            openPopup("Warning", "You have already booked an event, another can not be booked.");
        else
            goToBookEvent();
    }

    public void handleViewButton() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("View button pressed");

        // customer view event if it isn't booked
        if (!hasCustBookedEvent)
            openPopup("Warning", "You have not booked any event yet.");
        else
            goToViewEvent();
    }

    ////////////////// SCENE SWITCHING //////////////

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
        window.setScene(new Scene(root, 915, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Editing Your Account");
        window.show();
    }

    public void goToSignIn() throws IOException {
        System.out.println("Loading Main Menu window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        Parent root = loader.load();

        // close current window
        Stage window = (Stage) signout_btn.getScene().getWindow();
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

    public void goToViewEvent() throws IOException {
        System.out.println("Loading view event window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("event_view.fxml"));
        Parent root = loader.load();

        //Get controller of view event scene
        event_view_Controller controller = loader.getController();

        // logged in customer has booked an event
        // take user to view event
        if (controller.setEventDetails(LoggedInUsers.getCust_id(), 1)) {
            // close current window
            Stage window = (Stage) viewEvent_btn.getScene().getWindow();
            window.close();

            // start new window for view event scene
            window = new Stage();
            window.setScene(new Scene(root, 900, 600));

            Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
            Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

            Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
            Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

            window.setTitle("Viewing Your Event");
            window.show();
        }

        // customer has not booked event
        // show user prompt that event is not booked

    }

    public void goToBookEvent() throws IOException {
        System.out.println("Loading book event window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("event_book.fxml"));
        Parent root = loader.load();

        //Get controller of book event scene
        event_book_Controller controller = loader.getController();
        controller.initBookEvent();

        // close current window
        Stage window = (Stage) bookEvent_btn.getScene().getWindow();
        window.close();

        // start new window for book event scene
        window = new Stage();
        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

        Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.setTitle("Book Event");
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
