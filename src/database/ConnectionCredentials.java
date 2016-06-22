package database;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;

/**
 * Hold and make available credentials to connect to SQL database.
 */
public class ConnectionCredentials {
    private String serverName = "";
    private String databaseName = "";
    private String username = "";
    private Key passwordKey;
    private byte[] encryptedPassword;

    /**
     * Constructor
     * @param serverName name/address of the server
     * @param databaseName name of the database
     * @param username user/login name on the database
     * @param password user/login password on the database (plain text)
     */
    public ConnectionCredentials(String serverName, String databaseName, String username, String password) {
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.username = username;
        encryptedPassword = encrypt(password);
    }

    /**
     * Encrypts password prior to storing in a ConnectionCredentials object
     * @param password
     * @return the encrypted password
     */
    private byte[] encrypt(String password) {
        // Encryption code based on
        // http://stackoverflow.com/a/32583766/3626537
        byte[] encrypted = {};
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            digester.update(String.valueOf(password).getBytes("UTF-8"));
            byte[] digest = digester.digest();
            passwordKey = new SecretKeySpec(digest, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, passwordKey);
            encrypted = cipher.doFinal(password.getBytes());
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return encrypted;
    }

    /**
     * Format credentials to a String.
     * @return the credentials formatted as a String
     */
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
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Key getPasswordKey() {
        return passwordKey;
    }

    public byte[] getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setPassword(String password) {
        encryptedPassword = encrypt(password);
    }
}
