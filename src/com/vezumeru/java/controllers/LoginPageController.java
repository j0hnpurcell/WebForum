package com.vezumeru.java.controllers;

import static com.vezumeru.java.Main.*; // To access the sceneController and databaseHandler

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label loginMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginMessage.setVisible(false);
    }
    private void loginIfCredentialsAreValid(final String USERNAME, final String PASSWORD) {
        // Log user in if credentials are good
        if (databaseHandler.passwordGoesWithEnteredUsername(USERNAME, PASSWORD)) {
            sceneController.setWindowTitle(sceneController.homePageTitle);
            sceneController.setNewScene(sceneController.HOME_PAGE_FXML);
        } else {
            String ERROR_MESSAGE = "Wrong credentials. Try again..";
            loginMessage.setVisible(true);
            loginMessage.setText(ERROR_MESSAGE);
            loginMessage.setTextFill(Color.RED);
            passwordField.setText("");
        }
    }

    // User caused events
    public void loginButtonPressed() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        loginIfCredentialsAreValid(username, password);
    }
    public void signUpLinkPressed() {
        sceneController.setWindowTitle(sceneController.signUpPageTitle);
        sceneController.setNewScene(sceneController.SIGN_UP_PAGE_FXML);
    }
}
