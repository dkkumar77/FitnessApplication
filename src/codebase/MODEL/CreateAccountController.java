package codebase.MODEL;

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


                if (verifyEmail(txtEmail.getText())) {

                    if (!optDOB.isSelected()) {

                        if (Integer.parseInt(txtDOB.getValue().toString().substring(0, 4)) < 2010
                                && Integer.parseInt(txtDOB.getValue().toString().substring(0, 4)) >= 1965) {
                            if (!txtDOB.getValue().toString().equals("")) {


                                if (verifyEmail(txtEmail.getText())) {
                                    e.addData(txtUsername.getText(),
                                            new codebase.MODEL.BCrypt().hashPass(txtPassword.getText()), txtEmail.getText(),
                                            txtDOB.getValue().toString(), txtFirstName.getText(),
                                            txtLastName.getText());
                                    changeScene(event);
                                }

                            } else {
                                warning.setText("DOB is empty");
                                warning.setVisible(true);

                            }


                        } else {
                            warning.setText("Invalid Date");
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
                else{
                    warning.setText("Email isn't correct");
                    warning.setVisible(true);
                }


            }
        } else {
            warning.setText("Empty Fields");
            warning.setVisible(true);
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