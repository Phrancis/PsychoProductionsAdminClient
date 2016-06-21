package database;

import javax.crypto.Cipher;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Connect to SQL Server database using ConnectionCredentials object.
 */
public class DatabaseConnector {
    private String connectionUrl = "";
    private ConnectionCredentials credentials;

    public DatabaseConnector(ConnectionCredentials credentials) {
        this.credentials = credentials;
        this.connectionUrl = getConnectionUrl(credentials);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(
                    connectionUrl,
                    credentials.getUsername(),
                    decrypt(credentials.getEncryptedPassword())
            );
        } catch(Exception exc) {
            exc.printStackTrace();
        }
    }

    private String getConnectionUrl(ConnectionCredentials credentials) {
        String serverName = credentials.getServerName();
        String databaseName = credentials.getDatabaseName();
        String username = credentials.getUsername();

        connectionUrl = String.format(
                "jdbc:sqlserver://%s;databaseName=%s;",
                serverName,
                databaseName
        );
        return connectionUrl;
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
