package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EnterCodeController
{

    @FXML
    private JFXButton close, submit;


    @FXML
    private JFXTextField code;

    String username;


    @FXML
    void handleClose(ActionEvent event)
    {

        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();

    }

    @FXML
    void handleSubmit(ActionEvent event) throws SQLException, ClassNotFoundException
    {
        if(event.getSource().equals(submit))
        {
            Database e = new Database();

            if(code.getText().equals(e.getCode(this.username)))
            {
                Stage stage = (Stage) close.getScene().getWindow();
                stage.close();
            }
        }


    }

    public void setValue(String username)
    {
        this.username = username;
    }


}
