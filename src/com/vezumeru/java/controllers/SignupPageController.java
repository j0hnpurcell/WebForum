package com.vezumeru.java.controllers;

import static com.vezumeru.java.Main.*; // To access the sceneController and databaseHandler

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupPageController implements Initializable {

    @FXML private TextField usernameField;
    @FXML private PasswordField firstPasswordField;
    @FXML private PasswordField secondPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
    private void clearPasswordField(final String PROMPT_MESSAGE) {
        firstPasswordField.setText("");
        secondPasswordField.setText("");
        firstPasswordField.setPromptText(PROMPT_MESSAGE);
        secondPasswordField.setPromptText(PROMPT_MESSAGE);
    }
    private void registerUserIfCredentialsAreValid(String username, String password, String passwordRepeat) {
        final int MINIMUM_ALLOWED_CHARACTERS_IN_PASSWORD = 5;
        if (password.equals(passwordRepeat) && password.length() > MINIMUM_ALLOWED_CHARACTERS_IN_PASSWORD) {
            if (databaseHandler.userAlreadyExists(username)) {
                usernameField.setText("User already exists. Try another.");
                clearPasswordField("");
            } else {
                System.out.println("Signing up...");
                databaseHandler.addUserToTable(username, password);

                // Change the scene to the login page once they have register
                sceneController.setWindowTitle(sceneController.loginPageTitle);
                sceneController.setNewScene(sceneController.LOGIN_PAGE_FXML);
            }
        } else if (password.length() < MINIMUM_ALLOWED_CHARACTERS_IN_PASSWORD) {
            clearPasswordField("Password too weak (5 chars. min)");
        } else {
            clearPasswordField("Passwords do not match");
        }
    }
    public void signUpButtonPressed() {
        String username = usernameField.getText();
        String password = firstPasswordField.getText();
        String passwordRepeat = secondPasswordField.getText();

        registerUserIfCredentialsAreValid(username, password, passwordRepeat);
    }
    public void loginLinkPressed()  {
        sceneController.setWindowTitle(sceneController.loginPageTitle);
        sceneController.setNewScene(sceneController.LOGIN_PAGE_FXML);
    }
}
