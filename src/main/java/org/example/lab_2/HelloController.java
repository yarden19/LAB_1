package org.example.lab_2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Duration;

public class HelloController {

    @FXML private Label welcomeText;
    @FXML private TextField gmailText;
    @FXML private TextField passwordText;
    @FXML private Button CheckData;

    private static final UsersApp ua = new UsersApp();

    public void initialize() {
        ua.readData();
    }

    @FXML
    protected void onLogInClick() {
        String email = gmailText.getText();
        String password = passwordText.getText();

        new Thread(() -> {

            if(!ua.email_exist(email))
            {
                updateUI("this user name dosen't exist");
                return;
            }

            if (isUserLocked(email)) {
                return;
            }

            boolean success = ua.check_exist(email, password);

            if (success) {
                ua.resetAttempts(email);
                Platform.runLater(() -> loadNewScreen(email));
            } else {
                handleFailedLogin(email);
            }
        }).start();


}

    private boolean isUserLocked(String email) {
        LocalDateTime lockTime = ua.getLockTime(email);
        if (lockTime != null) {
            long secondsPassed = Duration.between(lockTime, LocalDateTime.now()).toSeconds();
            if (secondsPassed < HelloApplication.t) {
                long remaining = HelloApplication.t - secondsPassed;
                updateUI("blocked!! try again in  " + remaining + " seconds");
                return true;
            } else
            {
                ua.resetAttempts(email);
            }
        }
        return false;
    }

    private void handleFailedLogin(String email) {
        int attempts = ua.incrementFailedAttempts(email);
        if (attempts >= HelloApplication.n)
        {
            ua.setLockTime(email, LocalDateTime.now());
            updateUI("too much tries. you block for " + HelloApplication.t + " seconds");
        }
        else
        {
            updateUI("wrong details! try  " + attempts + " out of " + HelloApplication.n);
        }
    }

    private void updateUI(String message) {
        Platform.runLater(() -> welcomeText.setText(message));
    }

    private void loadNewScreen(String username) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/lab_2/wellcome_screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            WellcomeControler controller = fxmlLoader.getController();
            controller.setUsername(username);

            Stage stage = (Stage) CheckData.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Welcome!");
            stage.show();
        } catch (IOException e) {
            updateUI("Error loading new screen");
        }
    }
}