package database;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Hold and make available credentials to connect to SQL database.
 */
public class ConnectionCredentials {
    private String serverName = "";
    private final String DATABASE_NAME = "Francis";
    private String username = "";
    private byte[] encryptedPassword;

    public ConnectionCredentials(String serverName, String username, String password) {
        this.serverName = serverName;
        this.username = username;
        encryptedPassword = encrypt(password);
    }

    private byte[] encrypt(String password) {
        // Encryption code based on
        // http://stackoverflow.com/a/32583766/3626537
        byte[] encrypted = {};
        try {
            Key passwordKey = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, passwordKey);
            encrypted = cipher.doFinal(password.getBytes());
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return encrypted;
    }

    @Override
    public String toString() {
        return String.format(
                "serverName: %s | databaseName: %s | username: %s | password: HIDDEN",
                getServerName(),
                getDatabaseName(),
                getUsername()
        );
    }

    /*
    * Field getters and setters
    */
    private String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    private String getDatabaseName() {
        return DATABASE_NAME;
    }

    private String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private byte[] getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setPassword(String password) {
        encryptedPassword = encrypt(password);
    }
}
