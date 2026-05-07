package org.example.lab_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WellcomeControler {

    @FXML
    private Label welcomeUser;

    // פונקציה שמקבלת את השם ומציגה אותו
    public void setUsername(String username) {
        welcomeUser.setText("wellcome " + username);
    }

    @FXML
    protected void onLogoutClick() {
        System.out.println("Logout button clicked!");
    }
}