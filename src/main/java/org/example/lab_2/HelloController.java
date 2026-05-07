package org.example.lab_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField gmailText;

    @FXML
    private TextField passwordText;

    @FXML
    private Button CheckData;

    @FXML
    protected void onLogInClick() {

        UsersApp ua = new UsersApp();
        ua.readData();

        String email = gmailText.getText();
        String password = passwordText.getText();

        try {
            User user = new User(email, password);
        } catch (Exception e) {
            welcomeText.setText("wrong name or password");
            return; // עצירת הריצה אם יצירת האובייקט נכשלה
        }

        if (!ua.check_exist(email, password)) {
            welcomeText.setText("wrong name or password");
        } else {
            loadNewScreen(email);
        }

    }

    private void loadNewScreen(String username) {
        try {
            // הוספת הלוכסן (/) בתחילת הנתיב
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/lab_2/wellcome_screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);

            WellcomeControler controller = fxmlLoader.getController();
            controller.setUsername(username);

            Stage stage = (Stage) CheckData.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Welcome!");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            welcomeText.setText("Error loading new screen");
        }
    }
}