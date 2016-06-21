package gui.components;

import javax.swing.JPasswordField;
import java.awt.*;

/**
 * Simple single line password field (i.e. with characters hidden)
 */
public class TextFieldPasswordSingleLine extends JPasswordField {
    /**
     * Constructor with number of columns.
     * @param columns the number of columns
     */
    public TextFieldPasswordSingleLine(int columns) {
        super();
        setColumns(columns);
        setPreferredSize(new Dimension(30, 10));
        setMaximumSize(getPreferredSize());
    }
}
