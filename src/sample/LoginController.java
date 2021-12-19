package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TouchEvent;

import javax.swing.*;

public class LoginController {

    @FXML
    private JFXTextField btnLogin;

    @FXML
    private JFXPasswordField btnPassword;

    @FXML
    private JFXHamburger hmbMenu;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXButton btnForgotPass;

    @FXML
    private Label labelUserWarning, labelPasswordWarning;


    @FXML
    void handlePasswordEnter(KeyEvent event) {

        if(event.getCode().equals(KeyCode.ENTER)){
            btnPassword.setText("");
            btnLogin.setText("");


        }
    }

    @FXML
    void handleLoginEnter(KeyEvent event) {

        if(event.getCode().equals(KeyCode.ENTER)){
            btnPassword.setText("");
            btnLogin.setText("");

        }
    }


    @FXML
    void handleHover(TouchEvent event) {



    }

    @FXML
    void handleForgotPass(ActionEvent event)
    {
        if(event.getSource().equals(btnForgotPass))
        {



        }
    }

    @FXML
    void handleSubmit(ActionEvent event) {
        if(event.getSource().equals(btnSubmit))
        {

            if(btnLogin.getText().equals("") || btnPassword.getText().equals("")) {
                if (btnLogin.getText().equals("")) {
                    labelUserWarning.setText("Can't be empty");

                }
                if (btnPassword.getText().equals("")) {
                    labelPasswordWarning.setText("Can't be empty");

                }
                btnLogin.setText("");
                btnPassword.setText("");

            }
            else{
                labelUserWarning.setText("");
                labelPasswordWarning.setText("");



            }

        }
    }

}
