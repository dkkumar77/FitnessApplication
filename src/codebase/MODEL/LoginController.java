package codebase.MODEL;

import animatefx.animation.Wobble;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TouchEvent;

import java.io.IOException;

public class LoginController
{

    @FXML
    private JFXTextField btnLogin;

    @FXML
    private JFXPasswordField btnPassword;

    @FXML
    private JFXHamburger hmbMenu;

    @FXML
    private JFXButton btnSubmit, btnForgotPass, btnCreate;

    @FXML
    private Label labelUserWarning, labelPasswordWarning;


    @FXML
    void handleCreate(ActionEvent event) throws IOException
    {
        if(event.getSource().equals(btnCreate))
        {

            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML/CreateAccount.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new javafx.scene.Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }


    @FXML
    void handlePasswordEnter(KeyEvent event)
    {

        if(event.getCode().equals(KeyCode.ENTER))
        {
            btnPassword.setText("");
            btnLogin.setText("");

        }
    }

    @FXML
    void handleLoginEnter(KeyEvent event)
    {

        if(event.getCode().equals(KeyCode.ENTER))
        {
            btnPassword.setText("");
            btnLogin.setText("");

        }
    }


    @FXML
    void handleHover(TouchEvent event)  // Hamburger menu
    {



    }

    @FXML
    void handleForgotPass(ActionEvent event) throws IOException
    {
        if(event.getSource().equals(btnForgotPass))
        {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML/ForgotPassword.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new javafx.scene.Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    void handleSubmit(ActionEvent event) throws  java.sql.SQLException, ClassNotFoundException, java.io.IOException
    {

        Database e = new Database();

        if(event.getSource().equals(btnSubmit))
        {

            if(btnLogin.getText().equals("") || btnPassword.getText().equals(""))
            {
                if (btnLogin.getText().equals(""))
                {
                    new Wobble(btnSubmit).play();
                    labelUserWarning.setText("Can't be empty");

                }
                if (btnPassword.getText().equals(""))
                {
                    new Wobble(btnSubmit).play();
                    labelPasswordWarning.setText("Can't be empty");

                }
            }


                /**
                 * VALIDATION
                 */

                if(btnPassword.getText().equals(e.getPassword(btnLogin.getText())))
                {


                    javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML/Homepage.fxml"));
                    javafx.scene.Parent root = loader.load();

                    javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    Scene scene = new javafx.scene.Scene(root);
                    stage.setScene(scene);
                    stage.show();


                }
                else
                {

                    btnLogin.setText("");
                    btnPassword.setText("");

                }

                btnLogin.setText("");
                btnPassword.setText("");

            }

            else
            {
                labelUserWarning.setText("");
                labelPasswordWarning.setText("");

            }

        }


}


