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

    /* Nodes and their fx:id from SceneBuilder*/
    @FXML public static Parent signUpPageRoot;
    @FXML private TextField usernameField;
    @FXML private PasswordField firstPasswordField;
    @FXML private PasswordField secondPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private void clearFields() {
        usernameField.setText("");
        firstPasswordField.setText("");
        secondPasswordField.setText("");
    }
    /* Register user if credentials are valid and criterias are met */
    public void signUpButtonPressed() {
        String username = usernameField.getText();
        String password = firstPasswordField.getText();
        String passwordRepeat = secondPasswordField.getText();

        // Will create a function for this later on
        if (password.equals(passwordRepeat) && password.length() > 0) {
            System.out.println("Signing up succeeded");
            /* TODO: Make sure user doesn't exist */

            databaseHandler.addUserToTable(username, password);
            System.out.println();
            clearFields(); // This or set scene as homepage.. or login idk
        } else {
            firstPasswordField.setText("");
            secondPasswordField.setText("");
            firstPasswordField.setPromptText("Passwords do not match");
            secondPasswordField.setPromptText("Passwords do not match");
        }

    }
    /* Switch scene to LoginPage.xfml */
    public void loginLinkPressed()  { sceneController.setNewScene(sceneController.LOGIN_PAGE_FXML); }
}
