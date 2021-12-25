package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.sql.SQLException;

public class  CreateAccountController
{

    @FXML
    private JFXTextField txtFirstName, txtLastName, txtEmail, txtDOB, txtUsername;

    @FXML
    private JFXButton btnSubmit, btnBack;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label warning;


    public void handleSubmit(javafx.event.ActionEvent event) throws IOException, SQLException, ClassNotFoundException
    {


        if (event.getSource().equals(btnSubmit))
        {

        if (txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtEmail.getText().equals("")
                || txtPassword.getText().equals("") || txtDOB.getText().equals("") || txtUsername.getText().equals(""))
        {
            warning.setVisible(true);
        }
        else
        {

            if (verifyEmail(txtEmail.getText()))
            {

                Database e = new Database();

                e.addData(txtUsername.getText(),
                        txtPassword.getText(), txtEmail.getText(),
                        txtDOB.getText(), txtFirstName.getText(),
                        txtLastName.getText());


                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("src/sample/model/Login.fxml"));
                javafx.scene.Parent root = loader.load();

                javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                Scene scene = new javafx.scene.Scene(root);
                stage.setScene(scene);
                stage.show();

            }

        else
        {
            warning.setVisible(true);
        }

        }

        }

    }

    private boolean verifyEmail(String email)
    {

        try
        {
            InternetAddress e = new InternetAddress(email);
                e.validate();

            } catch(AddressException e ){
            return false;

        }

        return true;

    }

    @FXML
    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException
    {


        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("src/sample/model/Login.fxml"));
        javafx.scene.Parent root = loader.load();

        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new javafx.scene.Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}