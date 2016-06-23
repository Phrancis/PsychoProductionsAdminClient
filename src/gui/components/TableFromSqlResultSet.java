package gui.components;

import javax.swing.*;
import java.sql.ResultSet;

import utility.ResultSetToString2dArray;

/**
 * Create a Swing table from a SQL ResultSet
 */
public class TableFromSqlResultSet extends JTable {
    private String[] columnHeaders;
    private ResultSet resultSet;

    public TableFromSqlResultSet(String[] columnHeaders, ResultSet resultSet) {
        super(ResultSetToString2dArray.convert(resultSet), columnHeaders);
    }

}
