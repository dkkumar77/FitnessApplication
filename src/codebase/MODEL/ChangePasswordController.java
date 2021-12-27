package codebase.MODEL;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ChangePasswordController
{

    @FXML
    private JFXPasswordField txtNewPassword, txtConfirmPassword;

    @FXML
    private JFXButton btnClose, btnSubmit;

    String username;

    @FXML
    void handleClose(ActionEvent event)
    {
        if (event.getSource().equals(btnClose))
        {
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void handleSubmit(ActionEvent event) throws SQLException, ClassNotFoundException
    {
        if (event.getSource().equals(btnSubmit))
        {
            if (txtNewPassword.getText().equals(txtConfirmPassword.getText()))
            {
                Database e = new Database();
                e.updatePassword(username, new codebase.MODEL.BCrypt().hashPass(txtNewPassword.getText()));
                Stage stage = (Stage) btnClose.getScene().getWindow();
                stage.close();
            }

        }
    }

    void initializeUsername(String username)
    {
            this.username = username;
    }

}
