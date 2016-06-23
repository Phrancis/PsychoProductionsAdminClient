package utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Converts ResultSet object to String[][] object.
 */
public class ResultSetToString2dArray {
    public static String[][] convert(ResultSet resultSet) {
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
