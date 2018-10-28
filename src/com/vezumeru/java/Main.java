/* CREATED SUNDAY OCTOBER 28TH, 2018 (~11:41AM) by Jonathan Purcell */

package com.vezumeru.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application  {

    /* Fixed dimensions for window */
    private final int WIDTH  = 480;
    private final int HEIGHT = 400;

    private final String WINDOW_TITLE = "Web Forum V.1";

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/vezumeru/resources/views/LoginPage.fxml"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Makes the resolution fixed (cannot resize)
        stage.setMaxHeight(HEIGHT);
        stage.setMaxWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setMinWidth(WIDTH);

        stage.setTitle(WINDOW_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
