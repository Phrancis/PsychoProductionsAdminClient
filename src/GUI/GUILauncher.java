package gui;

import database.JdbcConnectionCredentials;
import gui.screens.MenuMain;
import gui.screens.SplashScreen;

import javax.swing.*;

/**
 * Sets certain parameters for the GUI and launches it
 */
public class GUILauncher {

    public static void launch(JdbcConnectionCredentials sessionJdbcCredentials) {
        System.out.println("Launching GUI");

        // Set the LookAndFeel
        try {
            for(UIManager.LookAndFeelInfo LAF : UIManager.getInstalledLookAndFeels()) {
                if("Nimbus".equals(LAF.getName())) {
                    UIManager.setLookAndFeel(LAF.getClassName());
                    System.out.println("Setting LookAndFeel to \"Nimbus\"");
                    break;
                }
            }
        } catch(Exception exc) {
            // use default LookAndFeel
            System.out.println("Setting LookAndFeel to \"Default\"");
        }

        // Launch the main menu passing it empty credentials created on startup
        SwingUtilities.invokeLater( () -> new MenuMain(sessionJdbcCredentials) );

        // Launch the splash screen of the GUI
        Runnable splash = SplashScreen::new;
        SwingUtilities.invokeLater(splash);
    }
}
