package codebase.MODEL;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

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
    void handleSubmit(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException
    {
        if(actionEvent.getSource().equals(submit))
        {
            Database e = new Database();

            if(code.getText().equals(e.getCode(this.username)))
            {
                Stage stage = (Stage) close.getScene().getWindow();
                stage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ChangePassword.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root));
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setMaxHeight(200);
                stage2.setMinHeight(200);
                stage2.setMaxWidth(300);
                stage2.setMinWidth(300);
                ChangePasswordController controller = fxmlLoader.getController();
                controller.initializeUsername(username);;



                AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
                AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);
                root.setOnMousePressed(event -> {
                    xOffset.set(event.getSceneX());
                    yOffset.set(event.getSceneY());
                });
                //move around here
                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - xOffset.get());
                    stage.setY(event.getScreenY() - yOffset.get());
                });
                stage2.initModality(Modality.APPLICATION_MODAL);
                stage2.show();


            }
        }


    }

    public void setValue(String username)
    {
        this.username = username;
    }


}
