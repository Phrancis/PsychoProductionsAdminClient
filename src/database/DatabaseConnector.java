package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connect to SQL Server database using ConnectionCredentials object.
 */
public class DatabaseConnector {
    // TODO implement JDBC Connection Pool like perhaps C3P0, Apache DPCP or Hikari
    // (thanks @BoristheSpider)

    private String connectionUrl = "";
    private ConnectionCredentials credentials;
    private Connection jdbcConnection;

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
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        jdbcConnection = DriverManager.getConnection(
                getConnectionUrl(credentials),
                credentials.getUsername(),
                credentials.getPassword()
        );
        return jdbcConnection;
    }

    public void closeJdbcConnection() throws SQLException {
        jdbcConnection.close();
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
