package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
     * @return the JDBC connection
     */
    public Connection getJdbcConnection() throws ClassNotFoundException, SQLException {
        Connection jdbcConnection;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            jdbcConnection = DriverManager.getConnection(
                    getConnectionUrl(credentials),
                    credentials.getUsername(),
                    credentials.getPassword()
            );
        } catch(Exception exc) {
            exc.printStackTrace();
            throw (exc);
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
}
