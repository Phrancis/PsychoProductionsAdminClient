package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utility.ResultSetUtil.print;
import static utility.ResultSetUtil.toString2dArray;

/**
 * SQL queries related to persons
 */
public class QueryPersons {
    private DatabaseConnector connector;

    public QueryPersons(JdbcConnectionCredentials credentials) {
        connector = new DatabaseConnector(credentials);
    }

    public String[][] getAllPersons() throws ClassNotFoundException, SQLException {
        try(Connection connection = connector.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "execute PsychoProductions.usp_GetAllPersons;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT))
        {
            ResultSet resultSet = statement.executeQuery();
            //print for testing
            print(resultSet);
            return toString2dArray(resultSet);
        }
        finally {
            try {
                connector.closeJdbcConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
