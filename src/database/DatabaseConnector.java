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

    /**
     * Constructor
     * @param credentials ConnectionCredentials object containing the required configuration to connect to the database server instance.
     */
    public DatabaseConnector(ConnectionCredentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Get a JDBC connection using ConnectionCredentials object.
     * @param credentials the ConnectionCredentials object to use to open JDBC connection
     * @return the JDBC connection
     */
    public Connection getJdbcConnection() {
        Connection jdbcConnection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            jdbcConnection = DriverManager.getConnection(
                    getConnectionUrl(credentials),
                    credentials.getUsername(),
                    decrypt(credentials.getEncryptedPassword())
            );
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return jdbcConnection;
    }

    /**
     * Builds the required connection URL required by DriverManager.
     * @param credentials ConnectionCredentials object containing the required configuration to connect to the database server instance.
     * @return the connection URL built from the credentials
     */
    private String getConnectionUrl(ConnectionCredentials credentials) {
        String serverName = credentials.getServerName();
        String databaseName = credentials.getDatabaseName();

        connectionUrl = String.format(
                "jdbc:sqlserver://%s;databaseName=%s;",
                serverName,
                databaseName
        );
        return connectionUrl;
    }

    /**
     * Decrypts data encrypted by ConnectionCredentials, e.g., a password.
     * @param encrypted the encrypted data
     * @return the decrypted data
     */
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
