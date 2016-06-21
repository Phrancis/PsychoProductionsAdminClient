package gui.screens;

import gui.components.*;

import javax.swing.*;

/**
 * Enter / Edit database credentials
 */
class FormCredentials {
    FormCredentials() {
        System.out.println("Launching FormCredentials");

        int frameWidth = 400;
        int frameHeight = 300;
        int onCloseAction = WindowConstants.HIDE_ON_CLOSE;

        //containers
        FrameDefault frame = new FrameDefault(frameWidth, frameHeight, onCloseAction);
        PanelBasic mainPanel = new PanelBasic(frameWidth - 5, frameHeight - 5);

        //components
        LabelTitleCentered title = new LabelTitleCentered("Credentials");

        ////fields
        JLabel lblServer = new JLabel("Server: ");
        TextFieldSingleLine fldServer = new TextFieldSingleLine(30);
        JLabel lblUsername = new JLabel("User Name: ");
        TextFieldSingleLine fldUsername = new TextFieldSingleLine(30);
        JLabel lblPassword = new JLabel("Password: ");
        TextFieldPasswordSingleLine fldPassword = new TextFieldPasswordSingleLine(30);

        ////buttons
        ButtonSimple btnVerifyCredentials = new ButtonSimple("Verify Credentials", "verifyCredentials", "Verify credentials for validity");
        ButtonSimple btnSaveCredentials = new ButtonSimple("Save Credentials", "saveCredentials", "Save credentials for this session");
        ButtonSimple btnCancel = new ButtonSimple("Cancel", "cancel", "Cancel and close this window");

        //layout
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        /* Server field */
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblServer)
                                .addComponent(fldServer))
                        /* Username field */
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUsername)
                                .addComponent(fldUsername))
                        /* Password field */
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPassword)
                                .addComponent(fldPassword))
                        .addComponent(btnVerifyCredentials)
                        .addComponent(btnSaveCredentials)
                        .addComponent(btnCancel)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title)
                        /* Server field */
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblServer)
                                .addComponent(fldServer))
                        /* Username field */
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblUsername)
                                .addComponent(fldUsername))
                        /* Password field */
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblPassword)
                                .addComponent(fldPassword))
                        .addComponent(btnVerifyCredentials)
                        .addComponent(btnSaveCredentials)
                        .addComponent(btnCancel)
        );

        //logic
        btnCancel.addActionListener(e -> frame.dispose());

        //finish
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
