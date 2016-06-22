package gui.screens;

import database.DatabaseConnector;
import database.JdbcConnectionCredentials;
import gui.components.*;

import javax.swing.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static javafx.application.Application.launch;

/**
 * Enter / Edit JDBC connection credentials
 */
class FormCredentials {
    JdbcConnectionCredentials sessionJdbcCredentials;

    /**
     * Constructor takes JDBC credentials and launches the form.
     * @param sessionJdbcCredentials the JDBC credentials
     */
    FormCredentials(JdbcConnectionCredentials sessionJdbcCredentials) {
        this.sessionJdbcCredentials = sessionJdbcCredentials;
        System.out.println("Launching FormCredentials");
        launch();
    }

    private void launch() {

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
        fldServer.setText(sessionJdbcCredentials.getServerName());
        JLabel lblDatabase = new JLabel("Database: ");
        TextFieldSingleLine fldDatabase = new TextFieldSingleLine(30);
        fldDatabase.setText(sessionJdbcCredentials.getDatabaseName());
        JLabel lblUsername = new JLabel("User Name: ");
        TextFieldSingleLine fldUsername = new TextFieldSingleLine(30);
        fldUsername.setText(sessionJdbcCredentials.getUsername());
        JLabel lblPassword = new JLabel("Password: ");
        TextFieldPasswordSingleLine fldPassword = new TextFieldPasswordSingleLine(30);
        fldPassword.setText(sessionJdbcCredentials.getPassword());

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
                        /* Database field */
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDatabase)
                                .addComponent(fldDatabase))
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
                        /* Database field */
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblDatabase)
                                .addComponent(fldDatabase))
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

        btnVerifyCredentials.addActionListener(e -> {
            JdbcConnectionCredentials testCredentials = new JdbcConnectionCredentials(
                    fldServer.getText(),
                    fldDatabase.getText(),
                    fldUsername.getText(),
                    new String(fldPassword.getPassword())
            );
            boolean pass = verifyJdbcConnection(testCredentials);
            if(pass) {
                JOptionPane.showMessageDialog(null, "Credentials validation PASSED.");
            } else {
                JOptionPane.showMessageDialog(null, "Credentials validation FAILED", "FAILED", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnSaveCredentials.addActionListener(e -> {
            sessionJdbcCredentials.setServerName(fldServer.getText());
            sessionJdbcCredentials.setDatabaseName(fldDatabase.getText());
            sessionJdbcCredentials.setUsername(fldUsername.getText());
            sessionJdbcCredentials.setPassword(new String(fldPassword.getPassword()));
            frame.dispose();
        });

        btnCancel.addActionListener(e -> frame.dispose());

        //finish
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Test the JDBC connection with provided credentials
     * @param testCredentials the JdbcConnectionCredentials to test
     * @return true if test was success, false otherwise
     */
    private boolean verifyJdbcConnection(JdbcConnectionCredentials testCredentials) {
        boolean pass = true;
        DatabaseConnector connector = new DatabaseConnector(testCredentials);
        Connection connection = null;
        try {
            connection = connector.getJdbcConnection();
        } catch(ClassNotFoundException | SQLException exc) {
            pass = false;
        }
        if(pass) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT 1 AS [pass];");
                while (resultSet.next()) {
                    if (resultSet.getInt("pass") == 1) {
                        pass = true;
                    }
                }
            } catch (SQLException exc) {
                pass = false;
            }
            try {
                connector.closeJdbcConnection();
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
        return pass;
    }
}
