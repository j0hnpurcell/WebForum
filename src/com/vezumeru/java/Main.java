/* Created October 28th, 2018 by Jonathan Purcell */

/* TODO
 * Refactor some of the code since I'm going to have to keep this maintainable for some bit (LATER)
 * I honestly don't know how much I'll update the project. It's more for fun to learn JavaFX, SQL, and Java in general
 *
 * Create separate function in the sign up controller for validating fields (DONE)
 * Allow users to actually log in (DONE)
 * Remove duplicate entries from MySQL (DONE)
 * Make it so where not the full path is included in the sceneController for the XFML files
 * Change window title (Using setTitle() or something) when scene is changed (DONE)
 * Reorganize the function order
 * Check IDE -- Fix the recommendations it gives you ---> (Yellow line)
 * I'll be vulnerable to SQL injection (LoginPageController) careful and check that
 * Put comments inside the function instead of outside see how that looks (DONE)
 * Allow users to login and sign up by pressing enter */

package com.vezumeru.java;

import com.vezumeru.java.controllers.SceneController;
import com.vezumeru.java.services.MySQLDatabaseHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    // Allows other controller classes to access the database and sceneController without creating an instance
    public static final MySQLDatabaseHandler databaseHandler = new MySQLDatabaseHandler();
    public static SceneController sceneController;

    @Override
    public void start(Stage stage) {
        // There was too much scene switching.. so just made a controller for that
        sceneController = new SceneController(stage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
