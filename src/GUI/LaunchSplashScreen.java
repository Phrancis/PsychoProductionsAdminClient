package GUI;

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

        DefaultFrame frame = new DefaultFrame(frameWidth, frameHeight, onCloseAction);

        JLabel label = new JLabel("Welcome to PsychoProductions Admin Client");
        frame.add(label);
        frame.setVisible(true);
    }

}
