package utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * ResultSet utility methods
 */
public class ResultSetUtil {

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

    public static String[][] toString2dArray(ResultSet resultSet) {
        /* Method based on http://stackoverflow.com/q/20021139/3626537 */
        String[][] resultArray;
        int rowSize = 0;
        int columnSize = 0;

        try {
            resultSet.last();
            rowSize = resultSet.getRow();
            resultSet.beforeFirst();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            columnSize = resultSetMetaData.getColumnCount();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }

        resultArray = new String[rowSize][columnSize];
        /*
         * SQL rows & columns are 1-indexed while Java arrays are 0-indexed,
         * hence the need for the +1 and -1 manipulations of indexes in this loop.
         */
        int row = 1;
        try {
            while (resultSet.next()) {
                for(int col = 1 ; col < columnSize +1; col++) {
                    resultArray[row-1][col-1] = resultSet.getString(col);
                }
                row++;
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return resultArray;
    }
}
