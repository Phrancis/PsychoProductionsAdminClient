package database;

/**
 * Hold and make available credentials to connect to SQL database.
 */
public class ConnectionCredentials {
    private String server = "";
    private String username = "";
    private String password = "";

    public ConnectionCredentials(String server, String username, String password) {
        this.server = server;
        this.username = username;
        this.password = password;
    }

    /*
    * Field getters and setters
    */
    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getPassword() {
//        return password;
//    }

    public void setPassword(String password) {
        this.password = password;
    }
}
