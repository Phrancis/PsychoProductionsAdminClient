package gui;

import gui.screens.MenuMain;
import gui.screens.SplashScreen;

import javax.swing.*;

/**
 * Sets certain parameters for the GUI and launches it
 */
public class GUILauncher {

    public static void launch() {
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
            exc.printStackTrace();
            // use default LookAndFeel
            System.out.println("Setting LookAndFeel to \"Default\"");
        }

        // Launch the first screen of the GUI
        Runnable splash = SplashScreen::new;
        Runnable mainMenu = MenuMain::new;
        SwingUtilities.invokeLater(mainMenu);
        SwingUtilities.invokeLater(splash);
    }
}
