package gui.components;

import javax.swing.JTextField;
import java.awt.*;

/**
 * Simple single line text field.
 */
public class TextFieldSingleLine extends JTextField {
    /**
     * Constructor with default parameters.
     */
    public TextFieldSingleLine(int columns) {
        super();
        setColumns(columns);
        setPreferredSize(new Dimension(30, 10));
        setMaximumSize(getPreferredSize());
    }
}
