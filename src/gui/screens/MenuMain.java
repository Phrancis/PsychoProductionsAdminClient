package gui.screens;

import database.JdbcConnectionCredentials;
import gui.components.ButtonSimple;
import gui.components.FrameDefault;
import gui.components.LabelTitleCentered;
import gui.components.PanelBasic;

import javax.swing.*;

/**
 * Top-level, main menu which will direct the user to the different sections of the client.
 */
public class MenuMain {
    private JdbcConnectionCredentials sessionJdbcCredentials;

    public MenuMain(JdbcConnectionCredentials sessionJdbcCredentials) {
        this.sessionJdbcCredentials = sessionJdbcCredentials;
        System.out.println("Launching MenuMain");
        launch();
    }

    private void launch() {
        int frameWidth = 400;
        int frameHeight = 600;
        int onCloseAction = WindowConstants.EXIT_ON_CLOSE;

        //containers
        FrameDefault frame = new FrameDefault(frameWidth, frameHeight, onCloseAction);
        PanelBasic mainPanel = new PanelBasic(frameWidth - 5, frameHeight - 5);

        //components
        LabelTitleCentered title = new LabelTitleCentered("Main Menu");
        ButtonSimple btnCredentials = new ButtonSimple("Credentials", "credentials", "Enter or edit database credentials");
        ButtonSimple btnManagePersons = new ButtonSimple("Manage Persons", "managePersons", "Open \"Manage Persons\" menu");
        ButtonSimple btnExit = new ButtonSimple("Exit", "exit", "Exit Admin Client");

        //layout
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        .addComponent(btnCredentials)
                        .addComponent(btnManagePersons)
                        .addComponent(btnExit)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(btnCredentials)
                        .addComponent(btnManagePersons)
                        .addComponent(btnExit)
        );

        //logic
        btnCredentials.addActionListener(e -> new FormCredentials(sessionJdbcCredentials));
        btnManagePersons.addActionListener(e -> new MenuManagePersons(sessionJdbcCredentials));

        btnExit.addActionListener(e -> frame.dispose());

        //finish
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }




}
