package GUI.IndividualComponents;

import javax.swing.JLabel;

/**
 * Centered title label
 */
public class LabelTitleCentered extends JLabel {
    public LabelTitleCentered(String labelText) {
        super();
        setText("<html><b>" + labelText + "</b></html>");
        setHorizontalAlignment(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.CENTER);
    }
}