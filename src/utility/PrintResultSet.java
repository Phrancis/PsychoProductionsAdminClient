package utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Prints the contents of a ResultSet to System.out
 */
public class PrintResultSet {
    public static void print(ResultSet resultSet) {
        /* Method based on http://stackoverflow.com/a/28165814/3626537 */
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) +": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
}
