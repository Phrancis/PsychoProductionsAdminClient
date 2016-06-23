package gui.components;

import javax.swing.*;
import java.sql.ResultSet;

import static utility.ResultSetUtil.toString2dArray;

/**
 * Create a Swing table from a SQL ResultSet
 */
public class TableFromSqlResultSet extends JTable {
    private String[] columnHeaders;
    private ResultSet resultSet;

    public TableFromSqlResultSet(String[] columnHeaders, ResultSet resultSet) {
        super(toString2dArray(resultSet), columnHeaders);
    }

}
