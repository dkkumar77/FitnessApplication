package codebase.MODEL;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.SQLException;


public class CreateAccountController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtFirstName, txtLastName, txtEmail, txtUsername;

    @FXML
    private JFXDatePicker txtDOB;

    @FXML
    private JFXButton btnSubmit, btnBack;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label warning;

    @FXML
    private JFXCheckBox optDOB;




    public void handleSubmit(javafx.event.ActionEvent event) throws IOException, SQLException, ClassNotFoundException {


        if (event.getSource().equals(btnSubmit)) {

            if (!emptyFields()){

                Database e = new Database();

                if (!optDOB.isSelected()) {                             // if DOB is not opted out

                    if (txtDOB.getValue() != null){   // if DOB is not empty

                        if (verifyEmail(txtEmail.getText())) {          // if email is verified

                            e.addData(txtUsername.getText(),            // store data
                                    new codebase.MODEL.BCrypt().hashPass(txtPassword.getText()),
                                    txtEmail.getText(), txtDOB.getValue().toString(), txtFirstName.getText(),
                                    txtLastName.getText());

                            changeScene(event);     // return to login
                        } else {    // else email is not verified
                            warning.setText("Invalid email address");
                            warning.setVisible(true);
                        }
                    } else {
                        warning.setText("DOB cannot be empty");
                        warning.setVisible(true);
                    }
                } else {    // else DOB is opted out

                    e.addData(txtUsername.getText(),    // store DOB as null
                            new codebase.MODEL.BCrypt().hashPass(txtPassword.getText()), txtEmail.getText(),
                            null, txtFirstName.getText(),
                            txtLastName.getText());
                    changeScene(event);

                }
            }
            else
            {
                warning.setText("Required fields cannot be empty");
                warning.setVisible(true);
            }
        }

    }

    public boolean emptyFields()
    {
        return (txtFirstName.getText().equals("") || txtLastName.getText().equals("")
                || txtEmail.getText().equals("")  || txtPassword.getText().equals("")
                || txtUsername.getText().equals(""));
    }

    private void changeScene(ActionEvent event) throws IOException {

        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML/Login.fxml"));
        javafx.scene.Parent root = loader.load();

        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new javafx.scene.Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void handleKeys(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            btnSubmit.fire();
        }

        if(event.getCode().equals(KeyCode.ESCAPE)){
            btnBack.fire();
        }
    }


    private boolean verifyEmail(String email) {

        try {
            InternetAddress e = new InternetAddress(email);
            e.validate();

        } catch (AddressException e) {
            return false;

        }

        return true;

    }

    @FXML
    public void handleBack(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource().equals(btnBack))
        {
            changeScene(actionEvent);
        }
    }

    public void handleOptDOB(javafx.event.ActionEvent actionEvent) {

        if (optDOB.isSelected()) {
            txtDOB.setDisable(true);
            txtDOB.setEffect(new Glow(0.8));

        }
        if (!optDOB.isSelected()) {
            txtDOB.setDisable(false);
            txtDOB.setEffect(null);
        }


    }
}