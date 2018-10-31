package com.vezumeru.java.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;

public class MySQLDatabaseHandler {
    private Connection connection;

    // General MySQL Database Methods
    private boolean isMySQLRunning() {
        // Check if service is running by checking if the port it runs on is open (Socket connects to port 3306 on localhost)
        final String HOST = "127.0.0.1";
        final int PORT = 3306; // MySQL default port

        try (Socket socket = new Socket(HOST, PORT)) { // If the socket connect then MySQL is running
            System.out.println(String.format("%s:%d is open", HOST, PORT));
            return true;
        } catch (UnknownHostException ex) {
            System.err.println("Unknown host: " + ex.getMessage() + " (Recheck the spelling)");
            return false;
        } catch (IOException ex) {
            // This happens when MySQL isn't running.. Since socket can't connect it throws an exception.
            // Considering the nature of the function you'd return false here
            // System.err.println("MySQL Database is not currently running.");
            return false;
        }
    }
    private void stopOrStartMySQLDatabase(final String COMMAND) {
        System.out.println("Starting database..");

        boolean MySQLIsRunning = isMySQLRunning();

        if (MySQLIsRunning) {
            System.out.println("MySQL seems to be running already. No need to start it");
        } else {
            try { // Try starting the database service if it's not running
                Runtime.getRuntime().exec(String.format("service mysql %s", COMMAND.toLowerCase()));
                System.out.println("MySQL service wasn't running, so I started it for you..");
            } catch (IOException ex) {
                System.err.println(ex.getMessage()); // Could not start MySQL service
                System.exit(1);
            }
        }
    }
    private void handleJDBC(final String DATABASE_NAME, final String USERNAME, final String PASSWORD) throws SQLException {
        final String URL = String.format("jdbc:mysql://localhost:3306/%s?useLegacyDatatimeCode=false&serverTimezone=America/Toronto&allowMultiQueries=true", DATABASE_NAME);

        // Register the JDBC Driver and open a connection
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        System.out.println("Database connected!"); // If an error wasn't thrown that means the we're connected to the database

        connection.setAutoCommit(false); // It's safer this way
    }

    // User methods
    public void addUserToTable(final String USERNAME, final String PASSWORD) {
        try {
            // Doesn't check if the user exists because "INSERT IGNORE" will ignore the query if the user exists?????
            String SQL = "INSERT INTO userCredentials (username, password) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, USERNAME);
            statement.setString(2, PASSWORD);

            statement.execute();

            connection.commit();
            statement.close();
        } catch (SQLException ex) { System.err.println(ex.getMessage()); }
    }
    // IGNORE FOR NOW.. I'LL IMPLEMENT LATER ON
    public void removeUserFromTable(final int ID) throws SQLException {
        try {
            // TODO Make it to where the user can delete their account if they wish refer to this to help
            // https://stackoverflow.com/questions/8555154/what-is-the-difference-between-drop-and-delete-for-tables
            String SQL = "DELETE FROM userCredentials WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setInt(1, ID);

            statement.execute();
            statement.close();
            connection.commit();
        } catch (SQLException ex) { System.err.println(ex.getMessage()); }
    }
    private String[] getMySQLLoginCredentialsFromUser() {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter username, press enter, and then enter the password for the database");
            return new String[]{userInput.readLine(), userInput.readLine()};
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
            return null;
        }
    }

    // Called when an instance of MySQLDatabaseHandler is created
    public MySQLDatabaseHandler()
    {
        // Table created with
        // CREATE TABLE userCredentials (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255) PRIMARY KEY, password VARCHAR(255));
        // I made it to where the id and username have to be unique. I might change that later on. Duplicate usernames?+
        try {
            // Starts the MySQL service /etc/init.d/mysql if it's not running
            stopOrStartMySQLDatabase("START");

            // String[] usernameAndPassword = getMySQLLoginCredentialsFromUser();
            // This is temporary, but bare with me now
            final String username = "test";
            final String password = "test";

            System.out.println("Trying to connect to the database..");
            // handleJDBC("WebForum", usernameAndPassword[0], usernameAndPassword[1]);
            handleJDBC("WebForum", username, password);

            // Clean-up the environment
            // connection.close();

            // stopOrStartMySQLDatabase("STOP");
        } catch (SQLException ex) { System.err.println(ex.getMessage()); }
    }
}