package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Admin_view_events_Controller implements Initializable {

    /////////////// TABLE VARIABLES //////////////////

    @FXML private TableView MainTable;
    @FXML private TableColumn<Event, String> event_id;
    @FXML private TableColumn<Event, String> event_name;
    @FXML private TableColumn<Event, String> event_type;
    @FXML private TableColumn<Event, String> event_date;
    @FXML private TableColumn<Event, String> event_guests;
    @FXML private TableColumn<Event, String> event_price;
    @FXML private TableColumn<Event, String> event_start;
    @FXML private TableColumn<Event, String> event_end;
    @FXML private TableColumn<Event, String> event_approved;
    @FXML private TableColumn<Event, String> event_verified;
    ////////////// TEXT VARIABLES ////////////////////

    @FXML private JFXTextField Text_Searchbar;
    @FXML private JFXTextField selectedEventID;

    ////////////// BUTTON VARIABLES //////////////////

    @FXML private JFXButton exit_btn;
    @FXML private JFXButton delete_btn;
    @FXML private JFXButton view_btn;

    // list to store data from DB
    private final ObservableList<Event> dataList = FXCollections.observableArrayList();

    ///////////////////////////////////////////////////

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        event_id.setCellValueFactory(new PropertyValueFactory<Event, String>("event_id"));
        event_name.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        event_type.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        event_date.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        event_guests.setCellValueFactory(new PropertyValueFactory<Event, String>("total_guests"));
        event_price.setCellValueFactory(new PropertyValueFactory<Event, String>("price"));
        event_start.setCellValueFactory(new PropertyValueFactory<Event, String>("starting_time"));
        event_end.setCellValueFactory(new PropertyValueFactory<Event, String>("ending_time"));
        event_approved.setCellValueFactory(new PropertyValueFactory<Event, String>("approved"));
        event_verified.setCellValueFactory(new PropertyValueFactory<Event, String>("verified"));
        Event event = new Event();
        ArrayList<Event> EventList = event.getListOfEvents();

        dataList.addAll(EventList);

        loadTable();
    }

    public void setFilter(FilteredList<Event> filteredData) {
        Text_Searchbar.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(employee -> {
            // If filter text is empty, display all persons.

            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if (employee.getEvent_id().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches id
            } else // Does not match.
                if (employee.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches name
            } else return employee.getType().toLowerCase().contains(lowerCaseFilter); // Filter matches last type
        }));
    }

    public void loadTable() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Event> filteredData = new FilteredList<>(dataList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        setFilter(filteredData);

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Event> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(MainTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        MainTable.setItems(sortedData);
    }

    public boolean checkID() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        if (selectedEventID == null || selectedEventID.getText().isEmpty()) {
            openPopup("Missing Input", "Please enter an event ID.");
            return false;
        }

        return true;
    }

    ///////////////////////////////////////////////////////////////

    // called when exit X button pressed
    public void handleExitButton() throws IOException {
        System.out.println("Exit button pressed");
        goToAdminMenu();
    }

    public void handleDeleteButton() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("Delete button pressed");

        if (!checkID()) {
            System.out.println("Input check failed");
            return;
        }

        String eventID = selectedEventID.getText();
        Event event = new Event();
        event.setEvent_id(eventID);

        int index = -1;

        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getEvent_id().equals(eventID)) {
                index = i;
                break;
            }
        }

        // invalid emp ID
        if (index == -1) {
            openPopup("Incorrect Input", "No record of an event with the entered event ID.");
        }

        else {
            dataList.remove(index);
            event.deleteEvent();
        }

        loadTable();
    }

    public void handleViewButton() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("View button pressed");

        if (!checkID()) {
            System.out.println("Input check failed");
            return;
        }

        goToViewEvent(selectedEventID.getText());
    }

    ///////////////// SCENE SWITCHING ////////////////

    public void goToAdminMenu() throws IOException {
        System.out.println("Loading Admin menu window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_menu.fxml"));
        Parent root = loader.load();

        //Get controller of main menu scene
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

    public void goToViewEvent(String eventID) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        System.out.println("Loading viewing event window");

        //Load next
        FXMLLoader loader = new FXMLLoader(getClass().getResource("event_view.fxml"));
        Parent root = loader.load();

        //Get controller of view event scene
        event_view_Controller controller = loader.getController();
        controller.disableExitButton();

        // logged in customer has booked an event
        // take user to view event
        if (controller.setEventDetails(eventID, 0)) {

            // start new window for view event scene
            Stage window = new Stage();
            window.setScene(new Scene(root, 900, 600));

            Font.loadFont(getClass().getResourceAsStream("Fonts/Alifiyah.otf"), 10);
            Font.loadFont(getClass().getResourceAsStream("Fonts/Honeymoon Avenue Script Demo.ttf"), 10);

            Font.loadFont(getClass().getResourceAsStream("Fonts/ArchivoNarrow-Regular.ttf"), 10);
            Font.loadFont(getClass().getResourceAsStream("Fonts/JuliusSansOne-Regular.ttf"), 10);

            window.setTitle("Viewing An Event");
            window.show();
        }

        // no event against the ID provided
        else {
            openPopup("Incorrect Input", "No record of an event with the entered event ID.");
        }
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
