package database;

/**
 * Hold and make available credentials to connect to SQL database.
 */
public class ConnectionCredentials {
    private String serverName = "";
    private String username = "";
    private String password = "";
    private final String DATABASE_NAME = "Francis";

    public ConnectionCredentials(String serverName, String username, String password) {
        this.serverName = serverName;
        this.username = username;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
