package gui.screens;

import gui.components.ButtonSimple;
import gui.components.FrameDefault;
import gui.components.LabelTitleCentered;
import gui.components.PanelBasic;

import javax.swing.*;

/**
 * Splash screen shown at launch.
 */
public class SplashScreen {

    public SplashScreen() {
        int frameWidth = 500;
        int frameHeight = 300;
        // temporary close action until app is more fleshed out:
        int onCloseAction = WindowConstants.EXIT_ON_CLOSE;

        System.out.println("Launching Splash Screen");

        //containers
        FrameDefault frame = new FrameDefault(frameWidth, frameHeight, onCloseAction);
        PanelBasic mainPanel = new PanelBasic(frameWidth - 5, frameHeight - 5);

        //components
        LabelTitleCentered title = new LabelTitleCentered("Welcome to PsychoProductions Admin Client");
        ButtonSimple buttonOK = new ButtonSimple("OK", "ok", "Close this window");

        //layout
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        .addComponent(buttonOK)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(buttonOK)
        );

        //finish
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
