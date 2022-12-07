package project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class popupcont {

    @FXML private JFXTextField head;
    @FXML private JFXTextArea text;
    @FXML private JFXButton ok_btn;

    ////////////////////////////////////

    public void setContent(String headText, String bodyText) {
        head.setText(headText);
        text.setText(bodyText);

        head.setEditable(false);
        text.setEditable(false);
    }

    //////////////////////////////////////

    public void closeWindow() {
         if (ok_btn != null) {
            Stage window = (Stage) ok_btn.getScene().getWindow();
            window.close();
        }
    }
}
