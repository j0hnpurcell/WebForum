package com.vezumeru.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupPageController implements Initializable {
    @FXML private Parent root;
    @FXML private TextField usernameField;
    @FXML private PasswordField firstPasswordField;
    @FXML private PasswordField secondPasswordField;
    @FXML private Button signup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void clearFields() {
        usernameField.setText("");
        firstPasswordField.setText("");
        secondPasswordField.setText("");
    }

    public void signUpButtonPressed() {
        String username = usernameField.getText();
        String password = firstPasswordField.getText();
        String passwordRepeat = secondPasswordField.getText();

        /* Make sure user doesn't exist */
        clearFields();
    }

    public void loginLinkPressed() throws IOException {
        /* Gets the current window */
        Stage stage = (Stage) root.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/vezumeru/resources/views/LoginPage.fxml"));
        Scene scene = new Scene(root, 480, 400);
        stage.setScene(scene);
        stage.show();
    }
}