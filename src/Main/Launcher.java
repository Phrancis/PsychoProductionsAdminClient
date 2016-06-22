package main;

import database.JdbcConnectionCredentials;
import gui.GUILauncher;

/**
 * Main class and entry point of the application.
 */
public class Launcher {
    public static void main(String[] args) {

        System.out.println("Launching PsychoProductionsAdminClient");

        JdbcConnectionCredentials sessionJdbcCredentials = new JdbcConnectionCredentials();

        GUILauncher.launch(sessionJdbcCredentials);
    }
}
