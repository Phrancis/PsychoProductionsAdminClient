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
        System.out.println("Launching SplashScreen");

        int frameWidth = 500;
        int frameHeight = 150;
        int onCloseAction = WindowConstants.DISPOSE_ON_CLOSE;

        //containers
        FrameDefault frame = new FrameDefault(frameWidth, frameHeight, onCloseAction);
        PanelBasic mainPanel = new PanelBasic(frameWidth - 5, frameHeight - 5);

        //components
        LabelTitleCentered title = new LabelTitleCentered("Welcome to PsychoProductions Admin Client");
        ButtonSimple btnClose = new ButtonSimple("Close", "close", "Close this window");

        //layout
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        .addComponent(btnClose)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(btnClose)
        );

        //logic
        btnClose.addActionListener(e -> frame.dispose());


        //finish
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
