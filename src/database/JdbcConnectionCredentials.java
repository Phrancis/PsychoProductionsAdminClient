package database;

/**
 * Hold and make available credentials to connect to SQL database.
 */
public class JdbcConnectionCredentials {
    private String serverName = "";
    private String databaseName = "";
    private String username = "";
    private String password = "";

    /**
     * Empty constructor used at application startup.
     */
    public JdbcConnectionCredentials() {
        /* intentionally empty */
    }

    /**
     * Constructor with required JDBC parameters.
     * @param serverName name/address of the server
     * @param databaseName name of the database
     * @param username user/login name on the database
     * @param password user/login password on the database (plain text)
     */
    public JdbcConnectionCredentials(String serverName, String databaseName, String username, String password) {
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
