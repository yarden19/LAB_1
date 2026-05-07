package org.example.lab_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WellcomeControler {

    @FXML
    private Label welcomeUser;

    public void setUsername(String username) {
        welcomeUser.setText("wellcome " + username);
    }
}