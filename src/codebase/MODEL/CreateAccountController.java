package codebase.MODEL;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class CreateAccountController {

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

            if (txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtEmail.getText().equals("")
                    || txtPassword.getText().equals("") || txtUsername.getText().equals("")) {
                warning.setVisible(true);
            } else {
                Database e = new Database();


                if (!optDOB.isSelected()) {
                    if (!txtDOB.getValue().toString().equals("")) {


                        if (verifyEmail(txtEmail.getText())) {



                            e.addData(txtUsername.getText(),
                                    new codebase.MODEL.BCrypt().hashPass(txtPassword.getText()), txtEmail.getText(),
                                    txtDOB.getValue().toString(), txtFirstName.getText(),
                                    txtLastName.getText());


                            changeScene(event);

                        }
                    } else {

                        warning.setVisible(true);

                    }
                } else {

                    e.addData(txtUsername.getText(),
                            new codebase.MODEL.BCrypt().hashPass(txtPassword.getText()), txtEmail.getText(),
                            "0", txtFirstName.getText(),
                            txtLastName.getText());
                    changeScene(event);


                }

            }
        } else {
            warning.setVisible(true);
        }

    }

    private void changeScene(ActionEvent event) throws IOException {

        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML/Login.fxml"));
        javafx.scene.Parent root = loader.load();

        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new javafx.scene.Scene(root);
        stage.setScene(scene);
        stage.show();

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
    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException {


        changeScene(actionEvent);


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