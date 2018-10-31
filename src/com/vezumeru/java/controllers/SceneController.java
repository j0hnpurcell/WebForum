package com.vezumeru.java.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SceneController {

    // Stage and properties to set it up
    private Stage stage;
    private final int WIDTH  = 480;
    private final int HEIGHT = 400;

    // Window titles
    public final String loginPageTitle = "https://www.WebForum.com/LoginPage.fxml";
    public final String signUpPageTitle = "https://www.WebForum.com/SignupPage.fxml";
    public final String homePageTitle = "https://www.WebForum.com/HomePage.fxml";

    // FXML Full Paths
    public final String LOGIN_PAGE_FXML = "/home/vezumeru/IdeaProjects/WebForum/src/com/vezumeru/resources/views/LoginPage.fxml";
    public final String SIGN_UP_PAGE_FXML  = "/home/vezumeru/IdeaProjects/WebForum/src/com/vezumeru/resources/views/SignupPage.fxml";
    public final String HOME_PAGE_FXML = "/home/vezumeru/IdeaProjects/WebForum/src/com/vezumeru/resources/views/HomePage.fxml";
    public final String DEFAULT_XFML_PAGE = LOGIN_PAGE_FXML;

    public void setWindowTitle(final String NEW_WINDOW_TITLE) { this.stage.setTitle(NEW_WINDOW_TITLE); }
    public void setNewScene(String XFML_FILE_LOCATION) {
        try {
            URL url = new File(XFML_FILE_LOCATION).toURL();
            stage.getScene().setRoot(FXMLLoader.load(url));
            stage.show();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    private void setStageProperties() {
        // Makes sure the user can't resize the screen
        stage.setMaxHeight(HEIGHT);
        stage.setMaxWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setMinWidth(WIDTH);
    }
    public void setInitialScene() {
        // This is so a 'null' exception isn't thrown in the setNewScene() method when it called stage.getScene()()
        try {
            Parent root = FXMLLoader.load(new File(DEFAULT_XFML_PAGE).toURL());
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            stage.setTitle(loginPageTitle);
            stage.setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        stage.show();
    }
    public SceneController(Stage s) {
        stage = s; // Save stage as a instance variable, so I can access it from other functions

        setStageProperties();
        setInitialScene();
    }
}
