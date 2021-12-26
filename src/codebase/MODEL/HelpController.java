package codebase.MODEL;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class HelpController {

    @FXML
    private JFXButton dgit;

    @FXML
    private JFXButton sgit;

    @FXML
    private JFXButton back;

    @FXML
    void handleBack(ActionEvent event) throws IOException {
        if(event.getSource().equals(back)){
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML/Login.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new javafx.scene.Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void handleGitOne(ActionEvent event) {
        if(event.getSource().equals(dgit))
        {
            openPage("https://github.com/dkkumar77");
        }
    }

    @FXML
    void handleGitTwo(ActionEvent event) {

        if(event.getSource().equals(sgit)){
            openPage("https://github.com/shahbajsingh");
        }
    }

    public static void openPage(String link) {
        try {
            Desktop.getDesktop().browse(new URL(link).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
