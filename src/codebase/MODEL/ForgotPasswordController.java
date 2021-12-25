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

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class ForgotPasswordController
{

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton submit, back;

    @FXML
    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException
    {

        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML/Login.fxml"));
        javafx.scene.Parent root = loader.load();

        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new javafx.scene.Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private boolean verifyEmail(String email)
    {

        try {
            InternetAddress e = new InternetAddress(email);
            e.validate();

        } catch (AddressException e) {
            return false;

        }

        return true;

    }


    @FXML
    void handleSubmit(ActionEvent actionEvent) throws IOException, ClassNotFoundException, SQLException, MessagingException {


        if (actionEvent.getSource().equals(submit)) {

            Database e = new Database();
            if (e.usernameExists(username.getText())) {
                String email = e.getEmail(username.getText());

                Properties properties = new Properties();

                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");
                properties.put("mail.smtp.starttls.enable", "true");


                String sender = "DSFitnessApp@gmail.com";
                String senderPassword = "Tyrantboys64!";

                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, senderPassword);
                    }
                });


                String new_code = String.valueOf(new Random().nextInt(999999));




                Message message = message(session, email,new_code );
                e.updateCode(username.getText(),new_code);



                Transport.send(message);
                

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/EnterCode.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setMaxHeight(200);
                stage.setMinHeight(200);
                stage.setMaxWidth(300);
                stage.setMinWidth(300);
                EnterCodeController controller = fxmlLoader.getController();
                controller.setValue(username.getText());



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
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

            }
        }

    }

    private Message message(Session session, String receiver, String code) {
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("DSFitnessApp@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject("Verify Email");
            message.setText("Code is:  \n" + code);
            return message;
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
        return null;
    }



}
