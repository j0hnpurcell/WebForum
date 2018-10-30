package com.vezumeru.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    /* Nodes and their fx:id from SceneBuilder*/
    @FXML private Parent root;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label loginMessage;

    /* In case I decided to change them later on and don't want to look through all the code */
    private final String SUPER_SECRET_USERNAME = "vezumeru";
    private final String SUCCESS_MESSAGE = "Login in.. Please wait.";
    private final String ERROR_MESSAGE = "Wrong credentials. Try again..";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginMessage.setVisible(false);
    }
    /* Check MySQL Database to see if the user is register */
    private boolean isValidCredentials(String username, String password) {
        /* This is temporary I'll eventually add MySQL possible add some form of password and username encryption? I don't care if it's weak for now*/
        return username.equals(SUPER_SECRET_USERNAME);
    }
    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
    /* Message changes depending on whether credentials were right or wrong */
    private void displayLoginMessage(boolean isValidCredentials) {
        loginMessage.setVisible(true);

        if (isValidCredentials) {
            loginMessage.setText(SUCCESS_MESSAGE);
            loginMessage.setTextFill(Color.GREEN);
        } else {
            loginMessage.setText(ERROR_MESSAGE);
            loginMessage.setTextFill(Color.RED);
        }
    }
    /* Log the user in if credentials are right*/
    public void loginButtonPressed() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        /* Login message changes depending on whether the credentials given were valid*/
        displayLoginMessage(isValidCredentials(username, password));
        clearFields();
    }
    /* Switch scene to SignupPage.xfml */
    public void signUpLinkPressed() throws IOException {
        /* Gets the current window */
        Stage stage = (Stage) root.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/vezumeru/resources/views/SignupPage.fxml"));
        Scene scene = new Scene(root, 480, 400);
        stage.setScene(scene);
        stage.show();
    }
}
