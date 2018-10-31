/* Created October 28th, 2018 by Jonathan Purcell */

/* TODO
 * Refactor some of the code since I'm going to have to keep this maintainable for some bit
 * I honestly don't know how much I'll update the project. It's more for fun to learn JavaFX, SQL, and Java in general
 *
 * Create separate function in the sign up controller for validating fields
 * Allow users to actually log in
 * Remove duplicate entries from MySQL (I did primary key.. I honestly don't know why duplicates are being allowed)
 * Make it so where not the full path is included in the sceneController for the XFML files */

package com.vezumeru.java;

import com.vezumeru.java.controllers.SceneController;
import com.vezumeru.java.services.MySQLDatabaseHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // Placing this here so other classes can access it
    public static final MySQLDatabaseHandler databaseHandler = new MySQLDatabaseHandler();
    public static SceneController sceneController;

    @Override
    public void start(Stage stage) throws IOException {
        // There was too much scene switching.. so just made a controller for that
        sceneController = new SceneController(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
