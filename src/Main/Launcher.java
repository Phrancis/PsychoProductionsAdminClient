package Main;

import GUI.LaunchSplashScreen;

import javax.swing.*;


/**
 * Main class and entry point of the application.
 */
public class Launcher {
    public static void main(String[] args) {

        System.out.println("PsychoProductionsAdminClient Launcher");

        Runnable splash = LaunchSplashScreen::new;

        SwingUtilities.invokeLater(splash);

    }
}
