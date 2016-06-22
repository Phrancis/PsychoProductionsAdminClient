package gui.screens;

import javax.swing.*;

import database.JdbcConnectionCredentials;
import gui.components.ButtonSimple;
import gui.components.FrameDefault;
import gui.components.LabelTitleCentered;
import gui.components.PanelBasic;

/**
 * Menu with selections related to Persons.
 */
public class MenuManagePersons {
    private JdbcConnectionCredentials sessionJdbcCredentials;

    public MenuManagePersons(JdbcConnectionCredentials sessionJdbcCredentials) {
        this.sessionJdbcCredentials = sessionJdbcCredentials;
        System.out.println("Launching MenuManagePersons");
        launch();
    }

    private void launch() {
        int frameWidth = 400;
        int frameHeight = 600;
        int onCloseAction = WindowConstants.DISPOSE_ON_CLOSE;

        //containers
        FrameDefault frame = new FrameDefault(frameWidth, frameHeight, onCloseAction);
        PanelBasic mainPanel = new PanelBasic(frameWidth - 5, frameHeight - 5);

        //components
        LabelTitleCentered title = new LabelTitleCentered("Manage Persons");
        ButtonSimple btnViewPersons = new ButtonSimple("View All Persons", "viewPersons", "View a list of all persons saved in database");
        ButtonSimple btnSearchPersons = new ButtonSimple("Search Persons", "searchPersons", "Search the database for persons");
        ButtonSimple btnAddPerson = new ButtonSimple("Add Person", "addPerson", "Add a new person to the database");
        ButtonSimple btnUpdatePerson = new ButtonSimple("Update Person", "updatePerson", "Update a person's information");
        ButtonSimple btnClose = new ButtonSimple("Close", "close", "Close this menu");

        //layout
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        .addComponent(btnViewPersons)
                        .addComponent(btnSearchPersons)
                        .addComponent(btnAddPerson)
                        .addComponent(btnUpdatePerson)
                        .addComponent(btnClose)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(btnViewPersons)
                        .addComponent(btnSearchPersons)
                        .addComponent(btnAddPerson)
                        .addComponent(btnUpdatePerson)
                        .addComponent(btnClose)
        );



        //finish
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
