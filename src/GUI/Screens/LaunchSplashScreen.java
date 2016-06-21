package gui.screens;

import gui.components.DefaultFrame;
import gui.components.LabelTitleCentered;

import javax.swing.*;

/**
 * Splash screen shown at launch.
 */
public class LaunchSplashScreen {

    public LaunchSplashScreen() {
        int frameWidth = 500;
        int frameHeight = 300;
        // temporary close action until app is more fleshed out:
        int onCloseAction = WindowConstants.EXIT_ON_CLOSE;

        System.out.println("Launching Splash Screen");

        DefaultFrame frame = new DefaultFrame(frameWidth, frameHeight, onCloseAction);

        LabelTitleCentered label = new LabelTitleCentered("Welcome to PsychoProductions Admin Client");
        frame.add(label);
        frame.setVisible(true);
    }

}
