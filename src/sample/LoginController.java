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
    void handleForgotPass(ActionEvent event) throws IOException
    {
        if(event.getSource().equals(btnForgotPass))
        {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new javafx.scene.Scene(root);
            stage.setScene(scene);
            stage.show();








        }
    }

    @FXML
    void handleSubmit(ActionEvent event) throws  java.sql.SQLException, ClassNotFoundException, java.io.IOException{

        Database e = new Database();

        if(event.getSource().equals(btnSubmit))
        {

            if(btnLogin.getText().equals("") || btnPassword.getText().equals("")) {
                if (btnLogin.getText().equals("")) {
                    labelUserWarning.setText("Can't be empty");

                }
                if (btnPassword.getText().equals("")) {
                    labelPasswordWarning.setText("Can't be empty");

                }
            }


                /**
                 * VALIDATION
                 */

                if(btnPassword.getText().equals(e.getPassword(btnLogin.getText()))){

                    javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("newClass.fxml"));
                    javafx.scene.Parent root = loader.load();

                    javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    Scene scene = new javafx.scene.Scene(root);
                    stage.setScene(scene);
                    stage.show();




                }
                else{
                    btnLogin.setText("");
                    btnPassword.setText("");

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


