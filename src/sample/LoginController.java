package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TouchEvent;

public class LoginController {

    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXHamburger touch;

    @FXML
    private JFXButton submit;

    @FXML
    void handleEnter(KeyEvent event) {

        if(event.getCode().equals(KeyCode.ENTER)){
            password.setText("");
            login.setText("");


        }
    }

    @FXML
    void handleEnterL(KeyEvent event) {
        
        if(event.getCode().equals(KeyCode.ENTER)){
            password.setText("");
            login.setText("");


        }
    }


    @FXML
    void handleHover(TouchEvent event) {

    }

    @FXML
    void handleSubmit(ActionEvent event) {

    }

}
