package gui.screens;

import gui.components.ButtonSimple;
import gui.components.FrameDefault;
import gui.components.LabelTitleCentered;
import gui.components.PanelBasic;

import javax.swing.*;

/**
 * Top-level, main menu which will direct the user to the different sections of the client.
 */
public class MenuMain {

    public MenuMain() {
        System.out.println("Launching MenuMain");

        int frameWidth = 400;
        int frameHeight = 600;
        int onCloseAction = WindowConstants.EXIT_ON_CLOSE;

        //containers
        FrameDefault frame = new FrameDefault(frameWidth, frameHeight, onCloseAction);
        PanelBasic mainPanel = new PanelBasic(frameWidth - 5, frameHeight - 5);

        //components
        LabelTitleCentered title = new LabelTitleCentered("Main Menu");
        ButtonSimple btnCredentials = new ButtonSimple("Credentials", "credentials", "Enter or edit credentials");

        //layout
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        .addComponent(btnCredentials)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(btnCredentials)
        );

        //logic
        btnCredentials.addActionListener(e -> new FormCredentials());

        //finish
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }




}
