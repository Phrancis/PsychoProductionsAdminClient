package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connect to SQL Server database using JdbcConnectionCredentials object.
 */
public class DatabaseConnector {
    // TODO implement JDBC Connection Pool like perhaps C3P0, Apache DPCP or Hikari
    // (thanks @BoristheSpider)

    private String connectionUrl = "";
    private JdbcConnectionCredentials credentials;
    private Connection jdbcConnection;

    /**
     * Constructor
     * @param credentials JdbcConnectionCredentials object containing the required configuration to connect to the database server instance.
     */
    public DatabaseConnector(JdbcConnectionCredentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Get a JDBC connection using JdbcConnectionCredentials object.
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
     * @param credentials JdbcConnectionCredentials object containing the required configuration to connect to the database server instance.
     * @return the connection URL built from the credentials
     */
    private String getConnectionUrl(JdbcConnectionCredentials credentials) {
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
