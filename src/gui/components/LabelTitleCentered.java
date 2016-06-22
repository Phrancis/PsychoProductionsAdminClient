package gui.components;

import javax.swing.JLabel;

/**
 * Centered title label
 */
public class LabelTitleCentered extends JLabel {
    public LabelTitleCentered(String labelText) {
        super();
        setText("<html><h3>" + labelText + "</h3></html>");
        setHorizontalAlignment(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.CENTER);
    }
}