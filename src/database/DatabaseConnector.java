package database;

import javax.crypto.Cipher;

/**
 * Connect to SQL database using ConnectionCredentials object.
 */
public class DatabaseConnector {
    private String connectionString = "";
    private ConnectionCredentials credentials;

    public DatabaseConnector(ConnectionCredentials credentials) {
        this.credentials = credentials;
        this.connectionString = getConnectionString(credentials);
    }

    private String getConnectionString(ConnectionCredentials credentials) {
        String serverName = credentials.getServerName();
        String databaseName = credentials.getDatabaseName();
        String username = credentials.getUsername();

        connectionString = String.format(
                "jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s",
                serverName,
                databaseName,
                username,
                decrypt(credentials.getEncryptedPassword())
        );
        return connectionString;
    }

    private String decrypt(byte[] encrypted) {
        String decrypted = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, credentials.getPasswordKey());
            decrypted = new String(cipher.doFinal(encrypted));
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return decrypted;
    }
}
