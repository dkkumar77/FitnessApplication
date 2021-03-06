package codebase.MODEL;

import animatefx.animation.Wobble;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField btnLogin;

    @FXML
    private JFXPasswordField btnPassword;

    @FXML
    private JFXHamburger hmbMenu;

    @FXML
    private JFXButton btnSubmit, btnForgotPass, btnCreate, btnHelp, btnClose;

    @FXML
    private Label labelUserWarning, labelPasswordWarning;

    @FXML
    void handleCreate(ActionEvent event) throws IOException {
        if (event.getSource().equals(btnCreate)) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/CreateAccount.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }


    @FXML
    void handleKeys(KeyEvent event) throws SQLException, IOException, ClassNotFoundException {

        if (event.getCode().equals(KeyCode.ENTER)) {
            btnSubmit.fire();
        }

        if(event.getCode().equals(KeyCode.ESCAPE)){
            btnClose.fire();
        }

        if(event.getCode().equals(KeyCode.SLASH)){
            btnHelp.fire();
        }

    }

    @FXML
    void handleForgotKeys(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            btnForgotPass.fire();
        }

        if(event.getCode().equals(KeyCode.ESCAPE)){
            root.setFocusTraversable(true);
        }

    }

    @FXML
    void handleCreateKeys(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            btnCreate.fire();
        }

        if(event.getCode().equals(KeyCode.ESCAPE)){
            root.setFocusTraversable(true);
        }

    }


    @FXML
    void handleHover(TouchEvent event)
    {


    }

    @FXML
    void handleForgotPass(ActionEvent event) throws IOException {
        if (event.getSource().equals(btnForgotPass)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ForgotPassword.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    void handleSubmit(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Database e = new Database();


        if (btnLogin.getText().equals("") || btnPassword.getText().equals("")) {
            if (btnLogin.getText().equals("")) {
                new Wobble(btnSubmit).play();
                labelUserWarning.setText("Can't be empty");

            }
            if (btnPassword.getText().equals("")) {
                new Wobble(btnSubmit).play();
                labelPasswordWarning.setText("Can't be empty");

            }
        }

        /**
         * VALIDATION
         */

        if (new codebase.MODEL.BCrypt().hashPass(btnPassword.getText()).equals(e.getPassword(btnLogin.getText()))) {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Homepage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } else {

            btnLogin.setText("");
            btnPassword.setText("");

        }

        btnLogin.setText("");
        btnPassword.setText("");


        }


    @FXML
    public void handleHelp(ActionEvent actionEvent) throws IOException
    {

        if(actionEvent.getSource().equals(btnHelp))
        {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Help.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }
    }

    @FXML
    public void handleClose(ActionEvent actionEvent) throws IOException
    {
        if(actionEvent.getSource().equals(btnClose))
        {
            System.exit(0);
        }
    }

}


