package codebase.MODEL;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class HelpController
{

    @FXML
    private JFXButton btnBack, btnDgit, btnSgit;

    @FXML
    void handleBack(ActionEvent event) throws IOException
    {
        if(event.getSource().equals(btnBack))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void handleDgit(ActionEvent event)
    {
        if(event.getSource().equals(btnDgit))
        {
            openPage("https://www.github.com/dkkumar77");
        }
    }

    @FXML
    void handleSgit(ActionEvent event)
    {

        if(event.getSource().equals(btnSgit))
        {
            openPage("https://www.github.com/shahbajsingh");
        }
    }

    public static void openPage(String link)
    {
        try
        {
            Desktop.getDesktop().browse(new URL(link).toURI());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
